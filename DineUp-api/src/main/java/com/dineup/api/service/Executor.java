package com.dineup.api.service;

import com.dineup.api.TargetConfig;
import com.dineup.api.exception.ClientException;
import com.dineup.api.exception.DetailedException;
import com.dineup.api.exception.ServiceException;
import com.dineup.api.service.error.ErrorResolver;
import com.dineup.api.service.error.exception.ServiceErrorException;
import com.dineup.api.service.error.exception.NetworkConnectionException;
import com.dineup.api.service.error.exception.ServiceNotFoundException;
import com.dineup.api.service.error.exception.UnexpectedMessageException;
import com.dineup.service.rest.ElementConfigKeys;
import com.dineup.service.rest.HeaderKeys;
import com.dineup.service.rest.RequestPath;
import com.dineup.util.Strings;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Executor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Executor.class);
    
    private final Client client;
    private final TargetConfig targetConfig;
    private final ErrorResolver errorResolver;
    
    public Executor(Client client, TargetConfig targetConfig) {
        if (client == null) {
            throw new IllegalArgumentException("client is null");
        }
        if (targetConfig == null) {
            throw new IllegalArgumentException("targetConfig is null");
        }
        this.errorResolver = new ErrorResolver(targetConfig.getLanguageCode());
        this.client = client;
        this.targetConfig = targetConfig;
    }
    
    public <T> T execute(Executable<T> executable) throws DetailedException {
        return execute(executable, executable);
    }
    
    public <T> T execute(TargetProvider targetProvider, ResponseParser<T> responseParser) throws DetailedException {
        try {
            return _execute(targetProvider, responseParser);
        }
        catch (ServiceException | ClientException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw errorResolver.resolveError(ex);
        }
    }
    
    private <T> T _execute(TargetProvider targetProvider, ResponseParser<T> responseParser) throws Exception {
        // Create the base request
        WebTarget target = client.target(targetConfig.getTarget())
                .path(RequestPath.ROOT_JSON);
        
        // Append the request with request path and parameters
        target = targetProvider.appendPath(target);
        Map<String, Object> parameters = new LinkedHashMap<>();
        targetProvider.putParameters(parameters);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            target = target.queryParam(entry.getKey(), entry.getValue());
        }
        parameters.clear();
        
        // Append the request with session parameters
        target = target.queryParam(ElementConfigKeys.LANGUAGE_CODE, targetConfig.getLanguageCode());
        
        LOGGER.debug("Target: " + target.getUri());
        
        // Execute request
        Response response;
        try {
            response = target.request().get();
        }
        catch (Exception ex) {
            throw new NetworkConnectionException(ex);
        }
        
        // Handle server-side error
        int statusCode = response.getStatus();
        String errorKey = response.getHeaderString(HeaderKeys.ERROR);
        if (!Strings.isEmptyText(errorKey)) {
            String errorMessage = StringEscapeUtils.unescapeJson(response.getHeaderString(HeaderKeys.ERROR_MESSAGE));
            String errorDescription = StringEscapeUtils.unescapeJson(response.getHeaderString(HeaderKeys.ERROR_DESCRIPTION));
            String localizedErrorMessage = StringEscapeUtils.unescapeJson(response.getHeaderString(HeaderKeys.LOCALIZED_ERROR_MESSAGE));
            String localizedErrorDescription = StringEscapeUtils.unescapeJson(response.getHeaderString(HeaderKeys.LOCALIZED_ERROR_DESCRIPTION));
            throw new ServiceException(statusCode, errorKey, errorMessage, localizedErrorMessage, errorDescription, localizedErrorDescription);
        }
        
        // Check status code
        switch (Response.Status.fromStatusCode(statusCode)) {
            case INTERNAL_SERVER_ERROR:
                throw new ServiceErrorException(response);
            case NOT_FOUND:
                throw new ServiceNotFoundException(response);
        }
        
        // Parse response
        try {
            return responseParser.parseResponse(response);
        }
        catch (Exception ex) {
            throw new UnexpectedMessageException(ex);
        }
    }
    
}

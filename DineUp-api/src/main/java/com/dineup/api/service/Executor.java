package com.dineup.api.service;

import com.dineup.api.TargetConfig;
import com.dineup.api.exception.LocalizedException;
import com.dineup.api.exception.ServiceException;
import com.dineup.service.rest.HeaderKeys;
import com.dineup.util.Strings;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringEscapeUtils;

class Executor {

    private final Client client;
    private final TargetConfig targetConfig;
    
    public Executor(Client client, TargetConfig targetConfig) {
        if (client == null) {
            throw new IllegalArgumentException("client is null");
        }
        if (targetConfig == null) {
            throw new IllegalArgumentException("targetConfig is null");
        }
        this.client = client;
        this.targetConfig = targetConfig;
    }
    
    public <T> T execute(Executable<T> executable) throws Exception {
        return execute(executable, executable);
    }
    
    public <T> T execute(TargetProvider targetProvider, ResponseParser<T> responseParser) throws Exception {
        // Build request
        WebTarget target = targetProvider.createTarget(client, targetConfig);
        
        // Execute request
        Response response;
        try {
            response = target.request().get();
        }
        catch (Exception ex) {
            throw new LocalizedException("Connection error", "Hálózati hiba", ex); // TODO: localize
        }
        
        // Handle server-side error
        String errorKey = response.getHeaderString(HeaderKeys.ERROR);
        if (!Strings.isEmptyText(errorKey)) {
            int statusCode = response.getStatus();
            String errorMessage = StringEscapeUtils.unescapeJson(response.getHeaderString(HeaderKeys.ERROR_MESSAGE));
            String errorDescription = StringEscapeUtils.unescapeJson(response.getHeaderString(HeaderKeys.ERROR_DESCRIPTION));
            String localizedErrorMessage = StringEscapeUtils.unescapeJson(response.getHeaderString(HeaderKeys.LOCALIZED_ERROR_MESSAGE));
            String localizedErrorDescription = StringEscapeUtils.unescapeJson(response.getHeaderString(HeaderKeys.LOCALIZED_ERROR_DESCRIPTION));
            throw new ServiceException(statusCode, errorKey, errorMessage, localizedErrorMessage, errorDescription, localizedErrorDescription);
        }
        
        // Parse response
        try {
            return responseParser.parseResponse(response);
        }
        catch (Exception ex) {
            throw new LocalizedException("Unexpected message format", "Nem várt üzenetformátum", ex); // TODO: localize
        }
    }
    
}

package com.dineup.api.service.httpclient;

import com.dineup.api.ApiConfig;
import com.dineup.api.exception.ClientException;
import com.dineup.api.exception.DetailedException;
import com.dineup.api.exception.ServiceException;
import com.dineup.api.service.error.ErrorResolver;
import com.dineup.api.service.httpclient.exception.ServiceErrorException;
import com.dineup.api.service.httpclient.exception.NetworkConnectionException;
import com.dineup.api.service.httpclient.exception.ServiceNotFoundException;
import com.dineup.api.service.httpclient.exception.UnexpectedMessageException;
import com.dineup.api.service.util.StringEscapeUtils;
import com.dineup.service.rest.ElementConfigKeys;
import com.dineup.service.rest.HeaderKeys;
import com.dineup.service.rest.RequestPath;
import com.dineup.util.Strings;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Executor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Executor.class);
    
    private final HttpClient client;
    private final ApiConfig targetConfig;
    private final ErrorResolver errorResolver;
    private final Gson gson = GsonFactory.createInstance();
    
    public Executor(SSLSocketFactory socketFactory, ApiConfig targetConfig) {
        if (socketFactory == null) {
            throw new IllegalArgumentException("socketFactory is null");
        }
        if (targetConfig == null) {
            throw new IllegalArgumentException("targetConfig is null");
        }
        //this.client = HttpClientBuilder.create().setSSLSocketFactory(socketFactory).build(); // TODO: apply ssl context without builder; Android does not contain the builder :-(
        this.client = new DefaultHttpClient();
        this.errorResolver = new ErrorResolver(targetConfig.getLanguageCode());
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
        StringBuilder target = Strings.buildConcat("/", targetConfig.getTarget(), RequestPath.ROOT_JSON);
        
        // Append the request with request path and parameters
        targetProvider.appendPath(target);
        Map<String, Object> parameters = new LinkedHashMap<>();
        targetProvider.putParameters(parameters);
        
        HttpParams params = new BasicHttpParams();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            params.setParameter(entry.getKey(), entry.getValue());
        }
        parameters.clear();
        
        // Append the request with session parameters
        params.setParameter(ElementConfigKeys.LANGUAGE_CODE, targetConfig.getLanguageCode());
        
        HttpRequestBase request = new HttpGet(target.toString());
        request.setParams(params); // TODO: do not work :-(
        
        LOGGER.debug("Target: " + request.toString());
        
        // Execute request
        HttpResponse response;
        try {
            response = client.execute(request);
            
        }
        catch (Exception ex) {
            throw new NetworkConnectionException(ex);
        }
        
        // Handle server-side error
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        String errorKey = getHeader(response, HeaderKeys.ERROR);
        if (!Strings.isEmptyText(errorKey)) {
            String errorMessage = StringEscapeUtils.unescapeJson(getHeader(response, HeaderKeys.ERROR_MESSAGE));
            String errorDescription = StringEscapeUtils.unescapeJson(getHeader(response, HeaderKeys.ERROR_DESCRIPTION));
            String localizedErrorMessage = StringEscapeUtils.unescapeJson(getHeader(response, HeaderKeys.LOCALIZED_ERROR_MESSAGE));
            String localizedErrorDescription = StringEscapeUtils.unescapeJson(getHeader(response, HeaderKeys.LOCALIZED_ERROR_DESCRIPTION));
            throw new ServiceException(statusCode, errorKey, errorMessage, localizedErrorMessage, errorDescription, localizedErrorDescription);
        }
        
        // Check status code
        switch (statusLine.getStatusCode()) {
            case HttpStatus.SC_INTERNAL_SERVER_ERROR:
                throw new ServiceErrorException();
            case HttpStatus.SC_NOT_FOUND:
                throw new ServiceNotFoundException();
        }
        
        // Parse response
        try {
            InputStream in = response.getEntity().getContent();
            Reader reader = new InputStreamReader(in);
            JsonReader jsonReader = new JsonReader(reader);
            T entity = responseParser.parseResponse(gson, jsonReader);
            jsonReader.close();
            reader.close();
            in.close();
            return entity;
        }
        catch (Exception ex) {
            throw new UnexpectedMessageException(ex);
        }
    }
    
    private static String getHeader(HttpResponse response, String key) {
        Header header = response.getFirstHeader(key);
        if (header == null || header.getValue() == null) {
            return "";
        }
        return header.getValue();
    }
    
}

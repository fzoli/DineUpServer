package com.dineup.api.service.impl;

import com.dineup.api.ApiConfig;
import com.dineup.api.ApiVersion;
import com.dineup.api.DineUpCache;
import com.dineup.api.SecurityConfig;
import com.dineup.api.exception.ClientException;
import com.dineup.api.exception.DetailedException;
import com.dineup.api.exception.ServiceException;
import com.dineup.api.service.error.ErrorResolver;
import com.dineup.api.service.error.exception.NetworkConnectionException;
import com.dineup.api.service.error.exception.ServiceErrorException;
import com.dineup.api.service.error.exception.ServiceNotFoundException;
import com.dineup.api.service.error.exception.UnexpectedMessageException;
import com.dineup.api.service.util.StringEscapeUtils;
import com.dineup.api.service.util.urlbuilder.UrlBuilder;
import com.dineup.service.rest.ElementConfigKeys;
import com.dineup.service.rest.HeaderKeys;
import com.dineup.service.rest.RequestPath;
import com.dineup.util.Strings;
import com.dineup.util.string.StringConcatenator;
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
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Executor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Executor.class);
    
    private final HttpClient client;
    private final ApiConfig apiConfig;
    private final ApiVersion apiVersion;
    private final HttpSecurityConfig httpSecurityConfig;
    private final ErrorResolver errorResolver;
    private final Gson gson = GsonFactory.createInstance();
    
    public Executor(ApiConfig apiConfig, ApiVersion apiVersion) {
        this.errorResolver = new ErrorResolver(apiConfig.getLanguageCode());
        this.apiConfig = apiConfig;
        this.apiVersion = apiVersion;
        this.httpSecurityConfig = createHttpSecurityConfig();
        this.client = createClient();
    }
    
    private HttpClient createClient() {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 10 * 1000); // Connection timeout.
        HttpConnectionParams.setSoTimeout(params, 20 * 1000); // Socket timeout.
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, "utf8");
        params.setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BEST_MATCH);
        SchemeRegistry sr = new SchemeRegistry();
        sr.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        sr.register(new Scheme("https", httpSecurityConfig.getSocketFactory(), 443));
        ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, sr);
        return new DefaultHttpClient(ccm, params);
    }
    
    private SecurityConfig getSecurityConfig() {
        return apiConfig.getSecurityConfig();
    }
    
    private HttpSecurityConfig createHttpSecurityConfig() {
        try {
            HttpSecurityConfig securityConfig = HttpSecurityConfig.newStrictSecurityConfig((X509HostnameVerifier) getSecurityConfig().getHostnameVerifier());
            if (getSecurityConfig().getKeyStore() != null) {
                securityConfig = HttpSecurityConfig.newCustomConfig(getSecurityConfig().getKeyStore(), (X509HostnameVerifier) getSecurityConfig().getHostnameVerifier());
            }
            return securityConfig;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void deleteCache(DineUpCache<?> cache) {
        try {
            cache.delete();
        }
        catch (Exception ex) {
            LOGGER.error("Failed to delete the cache", ex);
        }
    }
    
    private <T> DineUpCache.CacheRequest<T> cacheRequest(final T data, final String apiVersion, final String languageCode) {
        return new DineUpCache.CacheRequest<T>() {

            @Override
            public T data() {
                return data;
            }

            @Override
            public String apiVersion() {
                return apiVersion;
            }

            @Override
            public String languageCode() {
                return languageCode;
            }
        };
    }
    
    public <T> T execute(Executable<T> executable) throws DetailedException {
        return load(executable, executable);
    }
    
    public <T> T execute(CacheableExecutable<T> executable) throws DetailedException {
        return load(executable, executable, executable);
    }
    
    private <T> T load(TargetProvider targetProvider, ResponseParser<T> responseParser, Cacheable<T> cacheable) throws DetailedException {
        DineUpCache cache = cacheable.getCache();
        DineUpCache.CacheResult<T> result = null;
        try {
            result = cache.get();
        }
        catch (Exception ex) {
            LOGGER.error("Failed to load the cache", ex);
        }
        if (result != null) {
            String currentApiVersion = apiVersion == null ? null : apiVersion.getVersion();
            long age = result.lastModified() != null ? System.currentTimeMillis() - result.lastModified().getTime() : -1;
            boolean live = age >= 0 && age <= apiConfig.getCacheLifetime();
            boolean compatibleApi = result.apiVersion() != null && result.apiVersion().equals(currentApiVersion);
            boolean sameLanguage = result.languageCode() != null && result.languageCode().equalsIgnoreCase(apiConfig.getLanguageCode());
            if (live && compatibleApi && sameLanguage) {
                return result.data();
            }
        }
        T data = load(targetProvider, responseParser);
        try {
            cache.set(cacheRequest(data, apiVersion.getVersion(), apiConfig.getLanguageCode()));
        }
        catch (Exception ex) {
            LOGGER.error("Failed to cache the loaded data", ex);
        }
        return data;
    }
    
    private <T> T load(TargetProvider targetProvider, ResponseParser<T> responseParser) throws DetailedException {
        try {
            return download(targetProvider, responseParser);
        }
        catch (ServiceException | ClientException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw errorResolver.resolveError(ex);
        }
    }
    
    private <T> T download(TargetProvider targetProvider, ResponseParser<T> responseParser) throws Exception {
        // Create the base request
        StringConcatenator target = new StringConcatenator("/");
        target.addItems(apiConfig.getTarget().toString(), RequestPath.ROOT_JSON);
        
        // Append the request with request path and parameters
        targetProvider.appendPath(target);
        Map<String, Object> parameters = new LinkedHashMap<>();
        targetProvider.putParameters(parameters);
        
        UrlBuilder urlBuilder = UrlBuilder.fromString(target.toString());
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            urlBuilder = urlBuilder.addParameter(entry.getKey(), entry.getValue().toString());
        }
        target.clear();
        parameters.clear();
        
        // Append the request with session parameters
        urlBuilder = urlBuilder.addParameter(ElementConfigKeys.LANGUAGE_CODE, apiConfig.getLanguageCode());
        
        HttpRequestBase request = new HttpGet(urlBuilder.toUri());
        
        LOGGER.debug("Target: " + request.getURI());
        
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

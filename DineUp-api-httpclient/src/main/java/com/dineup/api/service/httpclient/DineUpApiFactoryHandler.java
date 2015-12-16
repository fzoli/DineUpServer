package com.dineup.api.service.httpclient;

import com.dineup.api.ApiVersion;
import com.dineup.api.DineUpApi;
import com.dineup.api.ApiConfig;
import com.dineup.api.ClientConfig;
import com.dineup.api.exception.DetailedException;
import com.dineup.api.service.convert.ApiVersionConverter;
import com.dineup.api.service.error.ErrorResolver;
import com.dineup.api.service.error.exception.UnsupportedApiException;
import com.dineup.api.service.httpclient.v1_0.ApiInitializer_v1_0;
import com.dineup.service.rest.RequestPath;
import com.dineup.util.Converters;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public final class DineUpApiFactoryHandler {

    private final ClientConfig clientConfig;
    private final ApiConfig targetConfig;
    
    private final Executor executor;
    private final ErrorResolver errorResolver;
    
    private final Map<ApiVersion, ApiInitializer> API_INITIALIZERS = new HashMap<ApiVersion, ApiInitializer>() {
        {
            put(ApiVersion.V1_0, ApiInitializer_v1_0.INSTANCE);
        }
    };
    
    public DineUpApiFactoryHandler(ClientConfig clientConfig, ApiConfig targetConfig) {
        this.clientConfig = clientConfig;
        this.targetConfig = targetConfig;
        this.executor = new Executor(clientConfig, targetConfig);
        this.errorResolver = new ErrorResolver(targetConfig.getLanguageCode());
    }
    
    public DineUpApi createInstance(ApiVersion version) {
        return API_INITIALIZERS.get(version).init(clientConfig, targetConfig);
    }
    
    public DineUpApi createInstance() throws DetailedException {
        List<ApiVersion> versions = getSupportedApiVersions();
        ApiVersion latestVersion = getLatestApiVersion(versions);
        return createInstance(latestVersion);
    }
    
    public List<ApiVersion> getSupportedApiVersions() throws DetailedException {
        return executor.execute(GetSupportedApiVersions.INSTANCE);
    }
    
    private ApiVersion getLatestApiVersion(List<ApiVersion> versions) throws DetailedException {
        List<ApiVersion> knownVersions = ApiVersion.getDecrescentVersions();
        for (ApiVersion knownVersion : knownVersions) {
            if (versions.contains(knownVersion)) {
                return knownVersion;
            }
        }
        throw errorResolver.resolveError(new UnsupportedApiException());
    }
    
    private static class GetSupportedApiVersions extends Executable<List<ApiVersion>> {

        private static final GetSupportedApiVersions INSTANCE = new GetSupportedApiVersions();
        
        public GetSupportedApiVersions() {
        }

        @Override
        public void appendPath(StringBuilder path) {
            path.append(RequestPath.PATH_SUPPORTED_API_VERSIONS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
        }

        @Override
        public List<ApiVersion> parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<List<String>>() {}.getType();
            List<String> entity = gson.fromJson(jsonReader, entityType);
            List<ApiVersion> list = toPublic(entity);
            return Collections.unmodifiableList(list);
        }
        
        private List<ApiVersion> toPublic(List<String> entity) {
            return Converters.convertList(entity, ApiVersionConverter.getInstance());
        }
        
    }
    
}

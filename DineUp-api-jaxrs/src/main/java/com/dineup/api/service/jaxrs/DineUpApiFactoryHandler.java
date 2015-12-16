package com.dineup.api.service.jaxrs;

import com.dineup.api.ApiVersion;
import com.dineup.api.DineUpApi;
import com.dineup.api.ApiConfig;
import com.dineup.api.ClientConfig;
import com.dineup.api.exception.DetailedException;
import com.dineup.api.service.convert.ApiVersionConverter;
import com.dineup.api.service.error.ErrorResolver;
import com.dineup.api.service.jaxrs.exception.UnsupportedApiException;
import com.dineup.api.service.jaxrs.v1_0.ApiInitializer_v1_0;
import com.dineup.service.rest.RequestPath;
import com.dineup.util.Converters;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public final class DineUpApiFactoryHandler {

    private final Client client;
    private final ApiConfig targetConfig;
    
    private final Executor executor;
    private final ErrorResolver errorResolver;
    
    private final Map<ApiVersion, ApiInitializer> API_INITIALIZERS = new HashMap<ApiVersion, ApiInitializer>() {
        {
            put(ApiVersion.V1_0, ApiInitializer_v1_0.INSTANCE);
        }
    };
    
    public DineUpApiFactoryHandler(ClientConfig clientConfig, ApiConfig targetConfig) {
        this.client = createClient(clientConfig);
        this.targetConfig = targetConfig;
        this.executor = new Executor(client, targetConfig);
        this.errorResolver = new ErrorResolver(targetConfig.getLanguageCode());
    }
    
    private Client createClient(ClientConfig config) {
        ClientBuilder builder = ClientBuilder.newBuilder();
        if (config.getHostnameVerifier() != null) {
            builder.hostnameVerifier(config.getHostnameVerifier());
        }
        if (config.getKeyStore() != null) {
            builder.keyStore(config.getKeyStore(), config.getKeyStorePassword());
        }
        if (config.getSslContext() != null) {
            builder.sslContext(config.getSslContext());
        }
        return builder.build();
    }
    
    public DineUpApi createInstance(ApiVersion version) {
        return API_INITIALIZERS.get(version).init(client, targetConfig);
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
        public WebTarget appendPath(WebTarget target) {
            return target.path(RequestPath.PATH_SUPPORTED_API_VERSIONS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
        }

        @Override
        public List<ApiVersion> parseResponse(Response response) {
            GenericType<List<String>> type = new GenericType<List<String>>(){};
            List<String> entity = response.readEntity(type);
            List<ApiVersion> list = toPublic(entity);
            return Collections.unmodifiableList(list);
        }
        
        private List<ApiVersion> toPublic(List<String> entity) {
            return Converters.convertList(entity, ApiVersionConverter.getInstance());
        }
        
    }
    
}

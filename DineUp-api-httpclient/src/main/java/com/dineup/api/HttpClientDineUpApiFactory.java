package com.dineup.api;

import com.dineup.api.exception.DetailedException;
import com.dineup.api.service.httpclient.DineUpApiFactoryHandler;

public final class HttpClientDineUpApiFactory implements DineUpApiFactory {

    private final DineUpApiFactoryHandler handler;
    
    public static DineUpApiFactory newFactory(ClientConfig clientConfig, ApiConfig apiConfig) {
        return new HttpClientDineUpApiFactory(clientConfig, apiConfig);
    }
    
    private HttpClientDineUpApiFactory(ClientConfig client, ApiConfig targetConfig) {
        handler = new DineUpApiFactoryHandler(client, targetConfig);
    }
    
    @Override
    public DineUpApi createInstance(ApiVersion version) {
        return handler.createInstance(version);
    }
    
    @Override
    public DineUpApi createInstance() throws DetailedException {
        return handler.createInstance();
    }
    
}

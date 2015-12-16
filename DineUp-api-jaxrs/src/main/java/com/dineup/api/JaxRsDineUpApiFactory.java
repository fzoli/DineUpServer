package com.dineup.api;

import com.dineup.api.exception.DetailedException;
import com.dineup.api.service.jaxrs.DineUpApiFactoryHandler;

public final class JaxRsDineUpApiFactory implements DineUpApiFactory {

    private final DineUpApiFactoryHandler handler;
    
    public static DineUpApiFactory newFactory(ClientConfig clientConfig, ApiConfig apiConfig) {
        return new JaxRsDineUpApiFactory(clientConfig, apiConfig);
    }
    
    private JaxRsDineUpApiFactory(ClientConfig client, ApiConfig targetConfig) {
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

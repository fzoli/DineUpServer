package com.dineup.api;

import com.dineup.api.exception.DetailedException;
import com.dineup.api.service.impl.DineUpApiFactoryHandler;

public final class DefaultDineUpApiFactory implements DineUpApiFactory {

    private final DineUpApiFactoryHandler handler;
    
    public static DineUpApiFactory newFactory(ApiConfig apiConfig) {
        return new DefaultDineUpApiFactory(apiConfig);
    }
    
    private DefaultDineUpApiFactory(ApiConfig apiConfig) {
        handler = new DineUpApiFactoryHandler(apiConfig);
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

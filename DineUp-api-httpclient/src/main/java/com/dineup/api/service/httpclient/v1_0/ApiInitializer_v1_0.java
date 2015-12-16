package com.dineup.api.service.httpclient.v1_0;

import com.dineup.api.ApiVersion;
import com.dineup.api.DineUpApi;
import com.dineup.api.ApiConfig;
import com.dineup.api.ClientConfig;
import com.dineup.api.service.httpclient.ApiInitializer;

public class ApiInitializer_v1_0 implements ApiInitializer {

    public static final ApiInitializer INSTANCE = new ApiInitializer_v1_0();
    
    @Override
    public DineUpApi init(ClientConfig clientConfig, ApiConfig targetConfig) {
        return new DineUpApiHandler(clientConfig, targetConfig, ApiVersion.V1_0);
    }
    
}

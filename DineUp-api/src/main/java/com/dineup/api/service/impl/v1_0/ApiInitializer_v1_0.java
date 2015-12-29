package com.dineup.api.service.impl.v1_0;

import com.dineup.api.ApiVersion;
import com.dineup.api.DineUpApi;
import com.dineup.api.ApiConfig;
import com.dineup.api.service.impl.ApiInitializer;

public class ApiInitializer_v1_0 implements ApiInitializer {

    public static final ApiInitializer INSTANCE = new ApiInitializer_v1_0();
    
    @Override
    public DineUpApi init(ApiConfig apiConfig) {
        return new DineUpApiHandler(apiConfig, ApiVersion.V1_0);
    }
    
}

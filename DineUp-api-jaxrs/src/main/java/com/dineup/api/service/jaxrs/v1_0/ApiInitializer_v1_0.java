package com.dineup.api.service.jaxrs.v1_0;

import com.dineup.api.ApiVersion;
import com.dineup.api.DineUpApi;
import com.dineup.api.ApiConfig;
import com.dineup.api.service.jaxrs.ApiInitializer;
import javax.ws.rs.client.Client;

public class ApiInitializer_v1_0 implements ApiInitializer {

    public static final ApiInitializer INSTANCE = new ApiInitializer_v1_0();
    
    @Override
    public DineUpApi init(Client client, ApiConfig targetConfig) {
        return new DineUpApiHandler(client, targetConfig, ApiVersion.V1_0);
    }
    
}

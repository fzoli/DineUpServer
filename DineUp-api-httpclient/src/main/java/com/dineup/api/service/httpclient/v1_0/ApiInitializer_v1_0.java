package com.dineup.api.service.httpclient.v1_0;

import com.dineup.api.ApiVersion;
import com.dineup.api.DineUpApi;
import com.dineup.api.ApiConfig;
import com.dineup.api.service.httpclient.ApiInitializer;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class ApiInitializer_v1_0 implements ApiInitializer {

    public static final ApiInitializer INSTANCE = new ApiInitializer_v1_0();
    
    @Override
    public DineUpApi init(SSLSocketFactory socketFactory, ApiConfig targetConfig) {
        return new DineUpApiHandler(socketFactory, targetConfig, ApiVersion.V1_0);
    }
    
}

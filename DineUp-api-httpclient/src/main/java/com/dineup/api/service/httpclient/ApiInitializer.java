package com.dineup.api.service.httpclient;

import com.dineup.api.DineUpApi;
import com.dineup.api.ApiConfig;
import com.dineup.api.ClientConfig;

public interface ApiInitializer {
    public DineUpApi init(ClientConfig clientConfig, ApiConfig targetConfig);
}
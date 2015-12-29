package com.dineup.api.service.impl;

import com.dineup.api.DineUpApi;
import com.dineup.api.ApiConfig;

public interface ApiInitializer {
    public DineUpApi init(ApiConfig apiConfig);
}
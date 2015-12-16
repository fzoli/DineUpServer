package com.dineup.api.service.httpclient;

import com.dineup.api.DineUpApi;
import com.dineup.api.ApiConfig;
import org.apache.http.conn.ssl.SSLSocketFactory;

public interface ApiInitializer {
    public DineUpApi init(SSLSocketFactory socketFactory, ApiConfig targetConfig);
}
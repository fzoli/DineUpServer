package com.dineup.api.service.jaxrs;

import com.dineup.api.DineUpApi;
import com.dineup.api.ApiConfig;
import javax.ws.rs.client.Client;

public interface ApiInitializer {
    public DineUpApi init(Client client, ApiConfig targetConfig);
}
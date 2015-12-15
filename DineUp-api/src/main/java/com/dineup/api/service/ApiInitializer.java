package com.dineup.api.service;

import com.dineup.api.DineUpApi;
import com.dineup.api.TargetConfig;
import javax.ws.rs.client.Client;

public interface ApiInitializer {
    public DineUpApi init(Client client, TargetConfig targetConfig);
}
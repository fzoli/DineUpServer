package com.dineup.api;

import com.dineup.api.service.DineUpApiHandler;
import javax.ws.rs.client.Client;

public class DineUpApiFactory {

    public static DineUpApi createInstance(Client client, TargetConfig targetConfig) {
        return new DineUpApiHandler(client, targetConfig);
    }
    
    private DineUpApiFactory() {
    }
    
}

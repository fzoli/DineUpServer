package com.dineup.api.service;

import com.dineup.api.TargetConfig;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public interface TargetProvider {
    public WebTarget createTarget(Client client, TargetConfig targetConfig);
}

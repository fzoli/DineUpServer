package com.dineup.api.service;

import java.util.Map;
import javax.ws.rs.client.WebTarget;

public interface TargetProvider {
    public WebTarget appendPath(WebTarget target);
    public void putParameters(Map<String, Object> parameters);
}

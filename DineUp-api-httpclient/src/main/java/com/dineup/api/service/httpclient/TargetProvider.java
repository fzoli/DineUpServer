package com.dineup.api.service.httpclient;

import java.util.Map;

public interface TargetProvider {
    public void appendPath(StringBuilder path);
    public void putParameters(Map<String, Object> parameters);
}

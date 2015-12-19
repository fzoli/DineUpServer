package com.dineup.api.service.httpclient;

import com.dineup.util.string.StringConcatenator;
import java.util.Map;

public interface TargetProvider {
    public void appendPath(StringConcatenator path);
    public void putParameters(Map<String, Object> parameters);
}

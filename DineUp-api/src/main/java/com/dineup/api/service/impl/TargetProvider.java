package com.dineup.api.service.impl;

import com.dineup.util.string.StringConcatenator;
import java.util.Map;

public interface TargetProvider {
    public void appendPath(StringConcatenator path);
    public void putParameters(Map<String, Object> parameters);
}

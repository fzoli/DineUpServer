package com.dineup.api.dom;

import com.dineup.api.ApiVersion;

public interface Service {
    public ApiVersion getApiVersion();
    public boolean isClientUpToDate();
}

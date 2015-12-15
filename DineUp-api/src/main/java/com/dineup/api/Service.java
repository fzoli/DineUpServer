package com.dineup.api;

public interface Service {
    public ApiVersion getApiVersion();
    public boolean isClientUpToDate();
}

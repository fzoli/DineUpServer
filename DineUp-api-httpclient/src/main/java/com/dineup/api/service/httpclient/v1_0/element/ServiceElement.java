package com.dineup.api.service.httpclient.v1_0.element;

import com.dineup.api.ApiVersion;
import com.dineup.api.dom.Service;
import com.dineup.api.service.convert.ApiVersionConverter;
import java.io.Serializable;

public class ServiceElement implements Service, Serializable {

    public String apiVersion;
    public boolean clientUpToDate;
    
    public ServiceElement() {
    }

    @Override
    public ApiVersion getApiVersion() {
        return ApiVersionConverter.getInstance().convert(apiVersion);
    }

    @Override
    public boolean isClientUpToDate() {
        return clientUpToDate;
    }
    
    @Override
    public String toString() {
        return String.format("Service(apiVersion=%s)", apiVersion);
    }
    
}

package com.dineup.api.service.jaxrs.v1_0.element;

import com.dineup.api.ApiVersion;
import com.dineup.api.dom.Service;
import com.dineup.api.service.convert.ApiVersionConverter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "service")
public class ServiceElement implements Service, Serializable {
    
    @XmlElement
    public String apiVersion;

    @XmlElement
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

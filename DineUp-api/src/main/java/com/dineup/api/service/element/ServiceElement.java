package com.dineup.api.service.element;

import com.dineup.api.Service;
import com.dineup.api.service.BuildConfig;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "service")
public class ServiceElement implements Service {
    
    @XmlElement
    int protocolVersion;

    public ServiceElement() {
    }

    @Override
    public int getClientProtocolVersion() {
        return BuildConfig.SERVICE_PROTOCOL_VERSION;
    }

    @Override
    public int getServerProtocolVersion() {
        return protocolVersion;
    }

    @Override
    public boolean isUpToDate() {
        return getClientProtocolVersion() == getServerProtocolVersion();
    }
    
    @Override
    public String toString() {
        return String.format("Service(protocolVersion=%s)", protocolVersion);
    }
    
}

package com.dineup.service.element;

import com.dineup.service.ServiceConfig;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "service")
public class ServiceElement {
    
    @XmlElement
    public int protocolVersion = ServiceConfig.PROTOCOL_VERSION;

    public ServiceElement() {
    }
    
}

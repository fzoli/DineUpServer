package com.dineup.rest.element;

import com.dineup.rest.ServiceConfig;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "service")
public class ServiceElement {
    
    @XmlElement
    public int protocolVersion = ServiceConfig.PROTOCOL_VERSION;

    public ServiceElement() {
    }
    
}

package com.dineup.service.element;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "service")
public class ServiceElement {
    
    @XmlElement
    public int protocolVersion;

    public ServiceElement() {
    }
    
}

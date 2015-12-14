package com.dineup.api.service.element;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "coordinate")
public class CoordinateElement implements Serializable {

    @XmlElement
    public double latitude;
    
    @XmlElement
    public double longitude;
    
    public CoordinateElement() {
    }
    
}

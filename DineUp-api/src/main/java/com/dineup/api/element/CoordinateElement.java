package com.dineup.api.element;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "coordinate")
public class CoordinateElement {

    @XmlElement
    public double latitude;
    
    @XmlElement
    public double longitude;
    
    public CoordinateElement() {
    }
    
}

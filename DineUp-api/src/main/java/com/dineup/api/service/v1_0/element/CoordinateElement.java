package com.dineup.api.service.v1_0.element;

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
    
    @Override
    public String toString() {
        return String.format("Coordinate(%s;%s)", latitude, longitude);
    }
    
}

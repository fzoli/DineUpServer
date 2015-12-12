package com.dineup;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "coordinate")
public class Coordinate {

    @XmlElement
    public double latitude;
    
    @XmlElement
    public double longitude;
    
    public Coordinate() {
    }
    
}

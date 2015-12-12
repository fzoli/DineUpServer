package com.dineup;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "restaurant")
public class Restaurant {
    
    @XmlAttribute
    public Integer id;
    
    @XmlElement
    public Double distance;

    @XmlElement
    public String type;
    
    @XmlElement
    public String name;
    
    @XmlElement
    public String description;
    
    @XmlElement
    public String openHours;
    
    @XmlElement
    public String photoUrl;
    
    @XmlElement
    public String address;

    @XmlElement
    public String defaultCurrency;
    
    @XmlElement
    public Coordinate coordinate;
    
    @XmlElement
    public double rating;
    
    public Restaurant() {
    }
    
}

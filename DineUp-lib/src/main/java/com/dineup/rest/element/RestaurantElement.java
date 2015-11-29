package com.dineup.rest.element;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "restaurant")
public class RestaurantElement {

    @XmlElement(name="name")
    public String name;
    
    public RestaurantElement() {
    }
    
}

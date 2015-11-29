package com.dineup.rest.element;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "restaurant")
public class RestaurantElement {

    @XmlElement
    public Integer id;
    
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
    public CoordinateElement coordinate;
    
    @XmlElement
    public int rating;
    
    @XmlElement
    public List<CategoryElement> categories;
    
    public RestaurantElement() {
    }
    
}

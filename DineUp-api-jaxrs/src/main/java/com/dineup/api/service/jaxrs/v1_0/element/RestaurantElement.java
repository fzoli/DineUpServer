package com.dineup.api.service.jaxrs.v1_0.element;

import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.Restaurant;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "restaurant")
public class RestaurantElement implements Restaurant, Serializable {
    
    @XmlAttribute
    public int id;
    
    @XmlElement
    public String languageCode;
    
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
    public CoordinateElement coordinate;
    
    @XmlElement
    public double rating;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public Double getDistance() {
        return distance;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getOpenHours() {
        return openHours;
    }

    @Override
    public String getPhotoUrl() {
        return photoUrl;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    @Override
    public Coordinate getCoordinate() {
        return new Coordinate(coordinate.latitude, coordinate.longitude);
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return String.format("Restaurant(id=%s)", id);
    }
    
    public RestaurantElement() {
    }
    
}

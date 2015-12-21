package com.dineup.api.service.jaxrs.v1_0.element;

import com.dineup.api.dom.Food;
import com.dineup.api.dom.Price;
import com.dineup.util.Lists;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class FoodElement implements Food, Serializable {

    @XmlAttribute
    public int id;
    
    @XmlElement
    public String languageCode;
    
    @XmlElement
    public String name;
    
    @XmlElement
    public String description;
    
    @XmlElement
    public String photoUrl;
    
    @XmlElement
    public double rating;
    
    @XmlElement
    public List<PriceElement> prices;
    
    public FoodElement() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
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
    public String getPhotoUrl() {
        return photoUrl;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public List<Price> getPrices() {
        return Lists.convert(prices);
    }
    
    @Override
    public String toString() {
        return String.format("Food(id=%s)", id);
    }
    
}

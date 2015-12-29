package com.dineup.api.service.impl.v1_0.element;

import com.dineup.api.service.dom.ServiceExtra;
import java.io.Serializable;

public class ExtraElement implements ServiceExtra, Serializable {

    public int id;
    public int restaurantId;
    public String languageCode;
    public String type;
    public String name;
    
    public ExtraElement() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getRestaurantId() {
        return restaurantId;
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
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
    public String toString() {
        return String.format("Extra(id=%s)", id);
    }
    
}

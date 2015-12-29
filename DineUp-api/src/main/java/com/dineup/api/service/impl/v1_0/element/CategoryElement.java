package com.dineup.api.service.impl.v1_0.element;

import com.dineup.api.service.dom.ServiceCategory;
import java.io.Serializable;

public class CategoryElement implements ServiceCategory, Serializable {
    
    public int id;
    public int restaurantId;
    public String languageCode;
    public String name;
    public String photoUrl;
    
    public CategoryElement() {
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
    public String getName() {
        return name;
    }

    @Override
    public String getPhotoUrl() {
        return photoUrl;
    }
    
    @Override
    public String toString() {
        return String.format("Category(id=%s)", id);
    }

}

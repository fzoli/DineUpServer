package com.dineup.api.service.impl.v1_0.element;

import com.dineup.api.dom.Price;
import com.dineup.api.service.dom.ServiceFood;
import com.dineup.util.Lists;
import java.io.Serializable;
import java.util.List;

public class FoodElement implements ServiceFood, Serializable {

    public int id;
    public int restaurantId;
    public String languageCode;
    public String name;
    public String description;
    public String photoUrl;
    public double rating;
    public List<PriceElement> prices;
    
    public FoodElement() {
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

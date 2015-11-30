package com.dineup.rest.element.converter;

import com.dineup.dom.Restaurant;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.RestaurantElement;

public class RestaurantElementConverter extends BaseElementConverter<Restaurant, RestaurantElement> {

    public RestaurantElementConverter(ElementConfig elementConfig) {
        super(elementConfig);
    }
    
    @Override
    public RestaurantElement safeConvert(Restaurant restaurant) {
        return new RestaurantElement(elementConfig, restaurant);
    }
    
}

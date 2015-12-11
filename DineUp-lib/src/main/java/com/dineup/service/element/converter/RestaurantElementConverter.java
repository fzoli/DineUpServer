package com.dineup.service.element.converter;

import com.dineup.service.ElementContext;
import com.dineup.dom.Restaurant;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.RestaurantElement;

public class RestaurantElementConverter extends BaseElementConverter<Restaurant, RestaurantElement> {

    public RestaurantElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }
    
    @Override
    public RestaurantElement safeConvert(Restaurant restaurant) {
        return new RestaurantElement(elementContext, elementConfig, restaurant);
    }
    
}

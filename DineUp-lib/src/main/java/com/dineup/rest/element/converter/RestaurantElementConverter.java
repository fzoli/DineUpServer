package com.dineup.rest.element.converter;

import com.dineup.dom.Restaurant;
import com.dineup.dom.RestaurantLocale;
import com.dineup.dom.Restaurants;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.RestaurantElement;
import com.dineup.util.Converter;

public class RestaurantElementConverter implements Converter<Restaurant, RestaurantElement> {

    private final ElementConfig elementConfig;
    
    public RestaurantElementConverter(ElementConfig elementConfig) {
        this.elementConfig = elementConfig;
    }
    
    @Override
    public RestaurantElement convert(Restaurant obj) {
        RestaurantLocale l = Restaurants.getLocale(obj, elementConfig.getLanguageCode());
        if (l == null) l = Restaurants.getLocale(obj, elementConfig.getDefaultLanguageCode());
        RestaurantElement e = new RestaurantElement();
        if (l != null) {
            e.name = l.getName();
        }
        return e;
    }
    
}

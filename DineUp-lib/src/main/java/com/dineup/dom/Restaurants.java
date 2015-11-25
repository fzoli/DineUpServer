package com.dineup.dom;

import java.util.List;

public class Restaurants {

    public static RestaurantLocale getLocale(Restaurant restaurant, String languageCode) {
        if (languageCode == null) {
            return null;
        }
        if (restaurant == null) {
            return null;
        }
        List<RestaurantLocale> locales = restaurant.getLocales();
        if (locales == null) {
            return null;
        }
        for (RestaurantLocale locale : locales) {
            if (languageCode.equalsIgnoreCase(locale.getLanguageCode())) {
                return locale;
            }
        }
        return null;
    }
    
    private Restaurants() {
    }
    
}

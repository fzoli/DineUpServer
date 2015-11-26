package com.dineup.dom;

public class Restaurants {

    public static RestaurantLocale getLocale(Restaurant restaurant, String languageCode) {
        if (restaurant == null) {
            return null;
        }
        return Locales.getLocale(restaurant.getLocales(), languageCode);
    }
    
    private Restaurants() {
    }
    
}

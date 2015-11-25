package com.dineup.gson;

import com.dineup.dom.Locale;
import com.dineup.dom.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {
    
    public static final Gson createInstance(Locale locale) {
        return new GsonBuilder()
            .registerTypeHierarchyAdapter(Restaurant.class, new RestaurantSerializer(locale))
            .setPrettyPrinting()
            .create();
    }

    private GsonFactory() {
    }
    
}

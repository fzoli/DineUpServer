package com.dineup.gson;

import com.dineup.dom.Localization;
import com.dineup.dom.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {
    
    public static final Gson createInstance(Localization localization) {
        return new GsonBuilder()
            .registerTypeHierarchyAdapter(Restaurant.class, new RestaurantSerializer(localization))
            .setPrettyPrinting()
            .create();
    }

    private GsonFactory() {
    }
    
}

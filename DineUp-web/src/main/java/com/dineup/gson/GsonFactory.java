package com.dineup.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {
    
    private static final Gson GSON = new GsonBuilder()
            .setExclusionStrategies(new RestaurantExclusionStrategy())
            .setPrettyPrinting()
            .create();
    
    public static final Gson getInstance() {
        return GSON;
    }

    private GsonFactory() {
    }
    
}

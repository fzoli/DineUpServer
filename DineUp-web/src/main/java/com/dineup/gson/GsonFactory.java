package com.dineup.gson;

import com.dineup.dom.Category;
import com.dineup.dom.Food;
import com.dineup.dom.Localization;
import com.dineup.dom.Price;
import com.dineup.dom.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {
    
    public static final Gson createInstance(Localization localization) {
        return new GsonBuilder()
            .registerTypeHierarchyAdapter(Price.class, new PriceSerializer())
            .registerTypeHierarchyAdapter(Food.class, new FoodSerializer())
            .registerTypeHierarchyAdapter(Restaurant.class, new RestaurantSerializer(localization))
            .registerTypeHierarchyAdapter(Category.class, new CategorySerializer(localization))
            .setPrettyPrinting()
            .create();
    }

    private GsonFactory() {
    }
    
}

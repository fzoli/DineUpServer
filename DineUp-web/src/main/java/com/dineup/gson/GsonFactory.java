package com.dineup.gson;

import com.dineup.dom.Category;
import com.dineup.dom.Extra;
import com.dineup.dom.Food;
import com.dineup.dom.Option;
import com.dineup.dom.Price;
import com.dineup.dom.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {
    
    public static final Gson createInstance(SerializerConfig serializerConfig) {
        return new GsonBuilder()
            .registerTypeHierarchyAdapter(Throwable.class, new ThrowableSerializer(serializerConfig.isExceptionMessageDisabled()))
            .registerTypeHierarchyAdapter(Price.class, new PriceSerializer())
            .registerTypeHierarchyAdapter(Restaurant.class, new RestaurantSerializer(serializerConfig))
            .registerTypeHierarchyAdapter(Category.class, new CategorySerializer(serializerConfig))
            .registerTypeHierarchyAdapter(Food.class, new FoodSerializer(serializerConfig))
            .registerTypeHierarchyAdapter(Extra.class, new ExtraSerializer(serializerConfig))
            .registerTypeHierarchyAdapter(Option.class, new OptionSerializer(serializerConfig))
            .create();
    }

    private GsonFactory() {
    }
    
}

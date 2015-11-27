package com.dineup.gson;

import com.dineup.dom.Restaurant;
import com.dineup.dom.RestaurantLocale;
import com.dineup.dom.Restaurants;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

class RestaurantSerializer implements JsonSerializer<Restaurant>, RestaurantFields {
    
    private final SerializerConfig serializerConfig;
    
    public RestaurantSerializer(SerializerConfig serializerConfig) {
        this.serializerConfig = serializerConfig;
    }
    
    @Override
    public JsonElement serialize(Restaurant restaurant, Type type, JsonSerializationContext context) {
        if (restaurant == null) {
            return JsonNull.INSTANCE;
        }
        JsonObject object = new JsonObject();
        object.addProperty(ID, restaurant.getId());
        object.addProperty(RATING, restaurant.getRating());
        object.addProperty(TYPE, restaurant.getType());
        object.addProperty(ADDRESS, restaurant.getAddress());
        object.addProperty(PHOTO_URL, restaurant.getPhotoUrl());
        RestaurantLocale restaurantLocale = Restaurants.getLocale(restaurant, serializerConfig.getLanguageCode());
        if (restaurantLocale != null) {
            object.addProperty(NAME, restaurantLocale.getName());
            object.addProperty(DESCRIPTION, restaurantLocale.getDescription());
            object.addProperty(OPEN_HOURS, restaurantLocale.getOpenHours());
        }
        else {
            object.add(NAME, JsonNull.INSTANCE);
            object.add(DESCRIPTION, JsonNull.INSTANCE);
            object.add(OPEN_HOURS, JsonNull.INSTANCE);
        }
        object.add(COORDINATE, context.serialize(restaurant.getCoordinate()));
        object.add(CATEGORIES, context.serialize(restaurant.getCategories()));
        return object;
    }
    
}

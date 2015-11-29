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
        object.addProperty(DEFAULT_CURRENCY, restaurant.getDefaultCurrency());
        RestaurantLocale locale = Restaurants.getLocale(restaurant, serializerConfig.getLanguageCode());
        if (locale == null) {
            locale = Restaurants.getLocale(restaurant, serializerConfig.getDefaultLanguageCode());
        }
        if (locale != null) {
            object.addProperty(NAME, locale.getName());
            object.addProperty(DESCRIPTION, locale.getDescription());
            object.addProperty(OPEN_HOURS, locale.getOpenHours());
        }
        else {
            object.add(NAME, JsonNull.INSTANCE);
            object.add(DESCRIPTION, JsonNull.INSTANCE);
            object.add(OPEN_HOURS, JsonNull.INSTANCE);
        }
        object.add(COORDINATE, context.serialize(restaurant.getCoordinate()));
        if (serializerConfig.withNestedObjects()) {
            object.add(CATEGORIES, context.serialize(restaurant.getCategories()));
        }
        return object;
    }
    
}

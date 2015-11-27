package com.dineup.gson;

import com.dineup.dom.Food;
import com.dineup.dom.FoodLocale;
import com.dineup.dom.Foods;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

class FoodSerializer implements JsonSerializer<Food>, FoodFields {

    private final SerializerConfig serializerConfig;
    
    public FoodSerializer(SerializerConfig serializerConfig) {
        this.serializerConfig = serializerConfig;
    }

    @Override
    public JsonElement serialize(Food food, Type type, JsonSerializationContext context) {
        if (food == null) {
            return JsonNull.INSTANCE;
        }
        JsonObject object = new JsonObject();
        object.addProperty(ID, food.getId());
        object.addProperty(PHOTO_URL, food.getPhotoUrl());
        FoodLocale locale = Foods.getLocale(food, serializerConfig.getLanguageCode());
        if (locale == null) {
            locale = Foods.getLocale(food, serializerConfig.getDefaultLanguageCode());
        }
        if (locale != null) {
            object.addProperty(NAME, locale.getName());
            object.addProperty(DESCRIPTION, locale.getDescription());
        }
        else {
            object.add(NAME, JsonNull.INSTANCE);
            object.add(DESCRIPTION, JsonNull.INSTANCE);
        }
        object.add(PRICES, context.serialize(food.getPrices()));
        object.add(EXTRAS, context.serialize(food.getExtras()));
        return object;
    }
    
}

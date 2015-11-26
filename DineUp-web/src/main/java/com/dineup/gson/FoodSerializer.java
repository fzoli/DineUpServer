package com.dineup.gson;

import com.dineup.dom.Food;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class FoodSerializer implements JsonSerializer<Food>, FoodFields {

    @Override
    public JsonElement serialize(Food food, Type type, JsonSerializationContext context) {
        if (food == null) {
            return JsonNull.INSTANCE;
        }
        JsonObject object = new JsonObject();
        object.add(PRICES, context.serialize(food.getPrices()));
        return object;
    }
    
}

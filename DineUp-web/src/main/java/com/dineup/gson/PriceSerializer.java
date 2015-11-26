package com.dineup.gson;

import com.dineup.dom.Price;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

class PriceSerializer implements JsonSerializer<Price>, PriceFields {

    @Override
    public JsonElement serialize(Price price, Type type, JsonSerializationContext context) {
        if (price == null) {
            return JsonNull.INSTANCE;
        }
        JsonObject object = new JsonObject();
        object.addProperty(CURENCY, price.getCurrency());
        object.addProperty(AMOUNT, price.getAmount());
        return object;
    }
    
}

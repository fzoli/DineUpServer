package com.dineup.gson;

import com.dineup.dom.Locales;
import com.dineup.dom.Option;
import com.dineup.dom.OptionLocale;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

class OptionSerializer implements JsonSerializer<Option>, OptionFields {

    private final SerializerConfig serializerConfig;

    public OptionSerializer(SerializerConfig serializerConfig) {
        this.serializerConfig = serializerConfig;
    }
    
    @Override
    public JsonElement serialize(Option option, Type type, JsonSerializationContext context) {
        if (option == null) {
            return JsonNull.INSTANCE;
        }
        JsonObject object = new JsonObject();
        OptionLocale locale = Locales.getLocale(option, serializerConfig.getLanguageCode());
        if (locale == null) {
            locale = Locales.getLocale(option, serializerConfig.getDefaultLanguageCode());
        }
        if (locale != null) {
            object.addProperty(NAME, locale.getName());
        }
        else {
            object.add(NAME, JsonNull.INSTANCE);
        }
        object.add(PRICES, context.serialize(option.getPrices()));
        return object;
    }
    
}

package com.dineup.gson;

import com.dineup.dom.Extra;
import com.dineup.dom.ExtraLocale;
import com.dineup.dom.Extras;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

class ExtraSerializer implements JsonSerializer<Extra>, ExtraFields {

    private final SerializerConfig serializerConfig;
    
    public ExtraSerializer(SerializerConfig serializerConfig) {
        this.serializerConfig = serializerConfig;
    }

    @Override
    public JsonElement serialize(Extra extra, Type type, JsonSerializationContext context) {
        if (extra == null) {
            return JsonNull.INSTANCE;
        }
        JsonObject object = new JsonObject();
        object.addProperty(TYPE, extra.getType());
        ExtraLocale locale = Extras.getLocale(extra, serializerConfig.getLanguageCode());
        if (locale != null) {
            object.addProperty(NAME, locale.getName());
        }
        else {
            object.add(NAME, JsonNull.INSTANCE);
        }
        object.add(OPTIONS, context.serialize(extra.getOptions()));
        return object;
    }
    
}

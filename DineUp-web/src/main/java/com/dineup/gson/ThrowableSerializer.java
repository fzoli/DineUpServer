package com.dineup.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class ThrowableSerializer implements JsonSerializer<Throwable> {

    private final boolean disabled;

    public ThrowableSerializer(boolean disabled) {
        this.disabled = disabled;
    }
    
    @Override
    public JsonElement serialize(Throwable src, Type typeOfSrc, JsonSerializationContext context) {
        if (disabled) {
            return JsonNull.INSTANCE;
        }
        Throwable cause = getCause(src);
        if (cause == null || cause.getLocalizedMessage() == null) {
            return JsonNull.INSTANCE;
        }
        return new JsonPrimitive(cause.getLocalizedMessage());
    }
    
    private Throwable getCause(Throwable throwable) {
        if (throwable == null) {
            return null;
        }
        if (throwable.getCause() != null) {
            return getCause(throwable.getCause());
        }
        return throwable;
    }
    
}

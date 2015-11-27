package com.dineup.gson;

import com.dineup.dom.Categories;
import com.dineup.dom.Category;
import com.dineup.dom.CategoryLocale;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

class CategorySerializer implements JsonSerializer<Category>, CategoryFields {

    private final SerializerConfig serializerConfig;
    
    public CategorySerializer(SerializerConfig serializerConfig) {
        this.serializerConfig = serializerConfig;
    }

    @Override
    public JsonElement serialize(Category category, Type type, JsonSerializationContext context) {
        if (category == null) {
            return JsonNull.INSTANCE;
        }
        JsonObject object = new JsonObject();
        object.addProperty(ID, category.getId());
        object.addProperty(PHOTO_URL, category.getPhotoUrl());
        CategoryLocale locale = Categories.getLocale(category, serializerConfig.getLanguageCode());
        if (locale == null) {
            locale = Categories.getLocale(category, serializerConfig.getDefaultLanguageCode());
        }
        if (locale != null) {
            object.addProperty(NAME, locale.getName());
        }
        else {
            object.add(NAME, JsonNull.INSTANCE);
        }
        object.add(FOODS, context.serialize(category.getFoods()));
        return object;
    }
    
}

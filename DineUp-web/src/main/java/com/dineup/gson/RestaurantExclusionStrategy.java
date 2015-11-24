package com.dineup.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

class RestaurantExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipClass(Class<?> arg0) {
        return false;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return (f.getName().equals("key"));
    }

}

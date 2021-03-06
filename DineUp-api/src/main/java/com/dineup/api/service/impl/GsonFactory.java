package com.dineup.api.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonFactory {

    public static Gson createInstance() {
        return new GsonBuilder()
                .create();
    }
    
    private GsonFactory() {
    }
    
}

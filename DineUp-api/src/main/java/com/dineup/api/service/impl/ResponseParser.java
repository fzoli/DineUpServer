package com.dineup.api.service.impl;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public interface ResponseParser<T> {
    public T parseResponse(Gson gson, JsonReader jsonReader);
}

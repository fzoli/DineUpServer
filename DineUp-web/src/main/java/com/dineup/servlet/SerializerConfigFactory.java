package com.dineup.servlet;

import com.dineup.gson.SerializerConfig;

import javax.servlet.http.HttpServletRequest;

class SerializerConfigFactory {

    private static final String KEY_NESTED_OBJECTS = "nestedObjects";
    private static final String KEY_LANGUAGE_CODE = "language";
    private static final String DEFAULT_LANGUAGE_CODE = "hu";

    public static SerializerConfig createInstance(HttpServletRequest request) {
        String languageCode = request.getParameter(KEY_LANGUAGE_CODE);
        if (languageCode == null) {
            languageCode = DEFAULT_LANGUAGE_CODE;
        }
        String nestedObjects = request.getParameter(KEY_NESTED_OBJECTS);
        boolean withNestedObjects = Boolean.parseBoolean(nestedObjects);
        return new SerializerConfig(languageCode, withNestedObjects);
    }

    private SerializerConfigFactory() {

    }

}

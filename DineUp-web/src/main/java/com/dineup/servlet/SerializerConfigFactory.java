package com.dineup.servlet;

import com.dineup.gson.SerializerConfig;

import javax.servlet.http.HttpServletRequest;

class SerializerConfigFactory {

    public static SerializerConfig createInstance(HttpServletRequest request) {
        String languageCode = request.getParameter("language");
        if (languageCode == null) {
            languageCode = "hu";
        }
        return new SerializerConfig(languageCode);
    }

    private SerializerConfigFactory() {

    }

}

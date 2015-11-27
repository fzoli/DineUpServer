package com.dineup.gson;

public class SerializerConfig {
    
    private final String languageCode;

    public SerializerConfig(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }
    
}

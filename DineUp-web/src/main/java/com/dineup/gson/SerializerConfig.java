package com.dineup.gson;

public class SerializerConfig {
    
    private final String languageCode;

    public SerializerConfig(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getDefaultLanguageCode() {
        return "en";
    }
    
    public String getLanguageCode() {
        return languageCode;
    }
    
}

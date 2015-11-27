package com.dineup.gson;

public class SerializerConfig {
    
    private final String languageCode;

    public SerializerConfig(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getDefaultLanguageCode() {
        return "en";
    }
    
    public boolean isExceptionMessageDisabled() {
        return false;
    }
    
    public String getLanguageCode() {
        return languageCode;
    }
    
}

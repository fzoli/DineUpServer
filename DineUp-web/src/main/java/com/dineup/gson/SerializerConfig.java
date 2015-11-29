package com.dineup.gson;

public class SerializerConfig {
    
    private final String languageCode;
    private final boolean withNestedObjects;

    public SerializerConfig(String languageCode, boolean withNestedObjects) {
        this.languageCode = languageCode;
        this.withNestedObjects = withNestedObjects;
    }

    public boolean withNestedObjects() {
        return withNestedObjects;
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

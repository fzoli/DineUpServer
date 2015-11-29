package com.dineup.rest;

public class ElementConfig {

    public interface Keys {
        String LANGUAGE_CODE = "language";
        String WITH_NESTED_OBJECTS = "withNestedObjects";
    }
    
    private final String languageCode;
    private final boolean withNestedObjects;

    private ElementConfig(Builder builder) {
        languageCode = builder.languageCode;
        withNestedObjects = builder.withNestedObjects;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getDefaultLanguageCode() {
        return "en";
    }
    
    public String getLanguageCode() {
        return languageCode;
    }

    public boolean withNestedObjects() {
        return withNestedObjects;
    }

    @Override
    public String toString() {
        return String.format("ElementConfig[languageCode=%s; withNestedObjects=%s]", languageCode, withNestedObjects);
    }

    public static final class Builder {
        
        private String languageCode;
        private boolean withNestedObjects;

        private Builder() {
        }

        public Builder languageCode(String languageCode) {
            if (languageCode == null) {
                languageCode = "en";
            }
            this.languageCode = languageCode;
            return this;
        }

        public Builder withNestedObjects(Boolean withNestedObjects) {
            if (withNestedObjects == null) {
                withNestedObjects = false;
            }
            this.withNestedObjects = withNestedObjects;
            return this;
        }

        public ElementConfig build() {
            return new ElementConfig(this);
        }
        
    }
    
}

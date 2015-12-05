package com.dineup.rest;

import com.dineup.dom.Coordinate;
import com.dineup.dom.Locale;
import com.dineup.dom.Locales;
import com.dineup.dom.LocalizedObject;
import com.dineup.rest.exception.InvalidParameterException;

public class ElementConfig {

    public interface Keys {
        String LANGUAGE_CODE = "language";
        String LATITUDE = "latitude";
        String LONGITUDE = "longitude";
        String WITH_NESTED_OBJECTS = "withNestedObjects";
    }
    
    private final String languageCode;
    private final Double latitude, longitude;
    private final boolean withNestedObjects;

    private ElementConfig(Builder builder) {
        languageCode = builder.languageCode;
        latitude = builder.latitude;
        longitude = builder.longitude;
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

    public String getPreferredLanguageCode() {
        if (languageCode == null) {
            return getDefaultLanguageCode();
        }
        return languageCode;
    }
    
    public Coordinate createCoordinate() {
        if (latitude != null && longitude != null) {
            return new Coordinate(latitude, longitude);
        }
        return null;
    }

    public boolean withNestedObjects() {
        return withNestedObjects;
    }

    public <ObjectType extends LocalizedObject<LocaleType>, LocaleType extends Locale> LocaleType getLocale(ObjectType object) {
        LocaleType l = Locales.getLocale(object, getLanguageCode());
        if (l == null) l = Locales.getLocale(object, getDefaultLanguageCode());
        return l;
    }
    
    public void validate() {
        if (latitude == null ^ longitude == null) {
            throw new InvalidParameterException("Invalid coordinate", /*TODO*/null);
        }
    }
    
    @Override
    public String toString() {
        return String.format("ElementConfig[languageCode=%s; withNestedObjects=%s]", languageCode, withNestedObjects);
    }

    public static final class Builder {
        
        private String languageCode;
        private Double latitude, longitude;
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

        public Builder coordinate(Coordinate coordinate) {
            if (coordinate == null) {
                latitude = null;
                longitude = null;
            }
            else {
                latitude = coordinate.getLatitude();
                longitude = coordinate.getLongitude();
            }
            return this;
        }
        
        public Builder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }
        
        public Builder longitude(Double longitude) {
            this.longitude = longitude;
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

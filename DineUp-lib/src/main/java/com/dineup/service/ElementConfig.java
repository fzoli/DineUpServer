package com.dineup.service;

import com.dineup.dom.Area;
import com.dineup.dom.Coordinate;
import com.dineup.dom.Locale;
import com.dineup.dom.Locales;
import com.dineup.dom.LocalizedObject;
import com.dineup.dom.Profile;
import com.dineup.ejb.profile.ProfileDescriptor;
import com.dineup.service.error.exception.IncompleteCoordinateException;
import com.dineup.service.error.exception.IncompleteAreaException;

/**
 * Request scope dependencies used by Elements.
 */
public class ElementConfig {
    
    private final String languageCode;
    private final Double latitude, longitude;
    private final Double radius;
    private final boolean withNestedObjects;
    private final String facebookAccessToken, googleAccessToken;

    public static Builder newBuilder() {
        return new Builder();
    }
    
    private ElementConfig(Builder builder) {
        languageCode = builder.languageCode;
        latitude = builder.latitude;
        longitude = builder.longitude;
        radius = builder.radius;
        withNestedObjects = builder.withNestedObjects;
        facebookAccessToken = builder.facebookAccessToken;
        googleAccessToken = builder.googleAccessToken;
    }

    public void validate() {
        if (latitude == null ^ longitude == null) {
            throw new IncompleteCoordinateException();
        }
        if (latitude == null && radius != null) {
            throw new IncompleteAreaException();
        }
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
    
    public Area createArea() {
        if (radius == null) {
            return null;
        }
        return new Area(createCoordinate(), radius);
    }
    
    public ProfileDescriptor createRequestProfileDescriptor() {
        if (facebookAccessToken != null) {
            return createProfileDescriptor(Profile.Type.FACEBOOK);
        }
        else if (googleAccessToken != null) {
            return createProfileDescriptor(Profile.Type.GOOGLE_PLUS);
        }
        return null;
    }
    
    public ProfileDescriptor createProfileDescriptor(Profile.Type profileType) {
        if (profileType == null) {
            return null;
        }
        String accessToken = null;
        switch (profileType) {
            case FACEBOOK:
                accessToken = facebookAccessToken;
                break;
            case GOOGLE_PLUS:
                accessToken = googleAccessToken;
                break;
        }
        return ProfileDescriptor.newBuilder()
                .accessToken(accessToken)
                .profileType(profileType)
                .build();
    }
    
    public <ObjectType extends LocalizedObject<LocaleType>, LocaleType extends Locale> LocaleType getLocale(ObjectType object) {
        LocaleType l = Locales.getLocale(object, getLanguageCode());
        if (l == null) l = Locales.getLocale(object, getDefaultLanguageCode());
        return l;
    }
    
    @Override
    public String toString() {
        return String.format("ElementConfig[languageCode=%s; withNestedObjects=%s]", languageCode, withNestedObjects);
    }

    public static final class Builder {
        
        private String languageCode;
        private Double latitude, longitude;
        private Double radius;
        private boolean withNestedObjects;
        private String facebookAccessToken;
        private String googleAccessToken;

        private Builder() {
        }

        public Builder facebookAccessToken(String facebookAccessToken) {
            this.facebookAccessToken = facebookAccessToken;
            return this;
        }
        
        public Builder googleAccessToken(String googleAccessToken) {
            this.googleAccessToken = googleAccessToken;
            return this;
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
        
        public Builder radius(Double radius) {
            this.radius = radius;
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

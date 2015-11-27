package com.dineup.builder;

import com.dineup.dom.RestaurantLocale;

public class RestaurantLocaleBuilder implements RestaurantLocale {

    private final String languageCode;
    private final String name;
    private final String description;
    private final String openHours;

    private RestaurantLocaleBuilder(Builder builder) {
        name = builder.name;
        description = builder.description;
        openHours = builder.openHours;
        languageCode = builder.languageCode;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getOpenHours() {
        return openHours;
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
    }

    public static final class Builder {
        private String languageCode;
        private String name;
        private String description;
        private String openHours;

        private Builder() {
        }

        public Builder languageCode(String languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder openHours(String openHours) {
            this.openHours = openHours;
            return this;
        }

        public RestaurantLocaleBuilder build() {
            return new RestaurantLocaleBuilder(this);
        }
    }
}

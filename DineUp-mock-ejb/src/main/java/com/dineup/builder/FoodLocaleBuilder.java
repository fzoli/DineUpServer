package com.dineup.builder;

import com.dineup.dom.FoodLocale;

public class FoodLocaleBuilder implements FoodLocale {
    private final String name;
    private final String description;
    private final String languageCode;

    private FoodLocaleBuilder(Builder builder) {
        name = builder.name;
        description = builder.description;
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
    public String getLanguageCode() {
        return languageCode;
    }

    public static final class Builder {
        private String name;
        private String description;
        private String languageCode;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder languageCode(String languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        public FoodLocaleBuilder build() {
            return new FoodLocaleBuilder(this);
        }
    }
}

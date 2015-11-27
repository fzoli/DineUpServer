package com.dineup.builder;

import com.dineup.dom.ExtraLocale;

public class ExtraLocaleBuilder implements ExtraLocale {
    private final String name;
    private final String languageCode;

    private ExtraLocaleBuilder(Builder builder) {
        name = builder.name;
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
    public String getLanguageCode() {
        return languageCode;
    }

    public static final class Builder {
        private String name;
        private String languageCode;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder languageCode(String languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        public ExtraLocaleBuilder build() {
            return new ExtraLocaleBuilder(this);
        }
    }
}

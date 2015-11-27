package com.dineup.builder;

import com.dineup.dom.Extra;
import com.dineup.dom.ExtraLocale;
import com.dineup.dom.Option;

import java.util.List;

public class ExtraBuilder implements Extra {

    private final String type;
    private final List<ExtraLocale> locales;
    private final List<Option> options;

    private ExtraBuilder(Builder builder) {
        type = builder.type;
        locales = builder.locales;
        options = builder.options;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public List<ExtraLocale> getLocales() {
        return locales;
    }

    @Override
    public List<Option> getOptions() {
        return options;
    }

    public static final class Builder {

        private String type;
        private List<ExtraLocale> locales;
        private List<Option> options;

        private Builder() {
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder locales(List<ExtraLocale> locales) {
            this.locales = locales;
            return this;
        }

        public Builder options(List<Option> options) {
            this.options = options;
            return this;
        }

        public ExtraBuilder build() {
            return new ExtraBuilder(this);
        }

    }

}

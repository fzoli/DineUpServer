package com.dineup.builder;

import com.dineup.dom.Option;
import com.dineup.dom.OptionLocale;
import com.dineup.dom.Price;

import java.util.List;

public class OptionBuilder implements Option {
    private final List<Price> prices;
    private final List<OptionLocale> locales;

    private OptionBuilder(Builder builder) {
        prices = builder.prices;
        locales = builder.locales;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public List<Price> getPrices() {
        return prices;
    }

    @Override
    public List<OptionLocale> getLocales() {
        return locales;
    }

    public static final class Builder {
        private List<Price> prices;
        private List<OptionLocale> locales;

        private Builder() {
        }

        public Builder prices(List<Price> prices) {
            this.prices = prices;
            return this;
        }

        public Builder locales(List<OptionLocale> locales) {
            this.locales = locales;
            return this;
        }

        public OptionBuilder build() {
            return new OptionBuilder(this);
        }
    }
}

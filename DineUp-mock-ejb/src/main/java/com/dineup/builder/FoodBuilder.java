package com.dineup.builder;

import com.dineup.dom.Extra;
import com.dineup.dom.Food;
import com.dineup.dom.FoodLocale;
import com.dineup.dom.Price;

import java.util.List;

public class FoodBuilder implements Food {

    private final Integer id;
    private final  String photoUrl;
    private final List<FoodLocale> locales;
    private final List<Price> prices;
    private final List<Extra> extras;

    private FoodBuilder(Builder builder) {
        id = builder.id;
        photoUrl = builder.photoUrl;
        locales = builder.locales;
        prices = builder.prices;
        extras = builder.extras;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getPhotoUrl() {
        return photoUrl;
    }

    @Override
    public List<FoodLocale> getLocales() {
        return locales;
    }

    @Override
    public List<Price> getPrices() {
        return prices;
    }

    @Override
    public List<Extra> getExtras() {
        return extras;
    }

    public static final class Builder {
        private Integer id;
        private String photoUrl;
        private List<FoodLocale> locales;
        private List<Price> prices;
        private List<Extra> extras;

        private Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder photoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
            return this;
        }

        public Builder locales(List<FoodLocale> locales) {
            this.locales = locales;
            return this;
        }

        public Builder prices(List<Price> prices) {
            this.prices = prices;
            return this;
        }

        public Builder extras(List<Extra> extras) {
            this.extras = extras;
            return this;
        }

        public FoodBuilder build() {
            return new FoodBuilder(this);
        }
    }
}

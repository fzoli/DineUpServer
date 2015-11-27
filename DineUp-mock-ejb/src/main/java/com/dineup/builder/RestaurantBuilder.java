package com.dineup.builder;

import com.dineup.dom.Category;
import com.dineup.dom.Coordinate;
import com.dineup.dom.Restaurant;
import com.dineup.dom.RestaurantLocale;

import java.util.List;

public class RestaurantBuilder implements Restaurant {

    private final Integer id;
    private final String type;
    private final String photoUrl;
    private final String address;
    private final String defaultCurrency;
    private final Coordinate coordinate;
    private final int rating;
    private final List<RestaurantLocale> locales;
    private final List<Category> categories;

    private RestaurantBuilder(Builder builder) {
        id = builder.id;
        type = builder.type;
        photoUrl = builder.photoUrl;
        address = builder.address;
        defaultCurrency = builder.defaultCurrency;
        coordinate = builder.coordinate;
        rating = builder.rating;
        locales = builder.locales;
        categories = builder.categories;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getPhotoUrl() {
        return photoUrl;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public List<RestaurantLocale> getLocales() {
        return locales;
    }

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    public static final class Builder {
        private Integer id;
        private String type;
        private String photoUrl;
        private String address;
        private String defaultCurrency;
        private Coordinate coordinate;
        private int rating;
        private List<RestaurantLocale> locales;
        private List<Category> categories;

        private Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder photoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder defaultCurrency(String defaultCurrency) {
            this.defaultCurrency = defaultCurrency;
            return this;
        }

        public Builder coordinate(Coordinate coordinate) {
            this.coordinate = coordinate;
            return this;
        }

        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder locales(List<RestaurantLocale> locales) {
            this.locales = locales;
            return this;
        }

        public Builder categories(List<Category> categories) {
            this.categories = categories;
            return this;
        }

        public RestaurantBuilder build() {
            return new RestaurantBuilder(this);
        }
    }

}

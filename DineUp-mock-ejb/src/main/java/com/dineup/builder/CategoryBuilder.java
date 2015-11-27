package com.dineup.builder;

import com.dineup.dom.Category;
import com.dineup.dom.CategoryLocale;
import com.dineup.dom.Food;

import java.util.List;

public class CategoryBuilder implements Category {

    private final Integer id;
    private final String photoUrl;
    private final List<CategoryLocale> locales;
    private final List<Food> foods;

    private CategoryBuilder(Builder builder) {
        id = builder.id;
        photoUrl = builder.photoUrl;
        locales = builder.locales;
        foods = builder.foods;
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
    public List<CategoryLocale> getLocales() {
        return locales;
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

    public static final class Builder {
        private Integer id;
        private String photoUrl;
        private List<CategoryLocale> locales;
        private List<Food> foods;

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

        public Builder locales(List<CategoryLocale> locales) {
            this.locales = locales;
            return this;
        }

        public Builder foods(List<Food> foods) {
            this.foods = foods;
            return this;
        }

        public CategoryBuilder build() {
            return new CategoryBuilder(this);
        }
    }
}

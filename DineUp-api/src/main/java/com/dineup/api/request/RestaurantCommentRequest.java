package com.dineup.api.request;

import com.dineup.api.dom.Restaurant;

public class RestaurantCommentRequest {
    private final Restaurant restaurant;
    private final String message;
    private final double rating;

    private RestaurantCommentRequest(Builder builder) {
        restaurant = builder.restaurant;
        message = builder.message;
        rating = builder.rating;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public String getMessage() {
        return message;
    }

    public double getRating() {
        return rating;
    }

    public static final class Builder {
        private Restaurant restaurant;
        private String message;
        private double rating;

        private Builder() {
        }

        public Builder restaurant(Restaurant restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder rating(double rating) {
            this.rating = rating;
            return this;
        }

        public RestaurantCommentRequest build() {
            return new RestaurantCommentRequest(this);
        }
    }
}

package com.dineup.api.request;

import com.dineup.api.dom.Food;

public class FoodCommentRequest {
    private final Food food;
    private final String message;
    private final double rating;

    private FoodCommentRequest(Builder builder) {
        food = builder.food;
        message = builder.message;
        rating = builder.rating;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Food getFood() {
        return food;
    }

    public String getMessage() {
        return message;
    }

    public double getRating() {
        return rating;
    }

    public static final class Builder {
        private Food food;
        private String message;
        private double rating;

        private Builder() {
        }

        public Builder food(Food food) {
            this.food = food;
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

        public FoodCommentRequest build() {
            return new FoodCommentRequest(this);
        }
    }
}

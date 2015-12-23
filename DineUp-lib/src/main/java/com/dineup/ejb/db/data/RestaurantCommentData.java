package com.dineup.ejb.db.data;

import com.dineup.dom.Restaurant;

public class RestaurantCommentData extends BaseCommentData {

    private final Restaurant restaurant;
    
    private RestaurantCommentData(Builder builder) {
        super(builder);
        this.restaurant = builder.restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static class Builder extends BaseBuilder<Builder> {
        private Restaurant restaurant;

        private Builder() {
        }
        
        public Builder restaurant(Restaurant restaurant) {
            this.restaurant = restaurant;
            return this;
        }
        
        public RestaurantCommentData build() {
            return new RestaurantCommentData(this);
        }
        
    }
    
}

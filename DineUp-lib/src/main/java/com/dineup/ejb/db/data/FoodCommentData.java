package com.dineup.ejb.db.data;

import com.dineup.dom.Food;

public class FoodCommentData extends BaseCommentData {

    private final Food food;
    
    private FoodCommentData(Builder builder) {
        super(builder);
        this.food = builder.food;
    }

    public Food getFood() {
        return food;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static class Builder extends BaseBuilder<Builder> {
        private Food food;

        private Builder() {
        }
        
        public Builder food(Food food) {
            this.food = food;
            return this;
        }
        
        public FoodCommentData build() {
            return new FoodCommentData(this);
        }
        
    }
    
}

package com.dineup.mock;

import com.dineup.dom.FoodLocale;

public class MockFoodLocale implements FoodLocale {

    private final int id;
    
    MockFoodLocale(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return "Food" + id;
    }

    @Override
    public String getDescription() {
        return "Food description" + id;
    }

    @Override
    public String getLanguageCode() {
        return "en";
    }
    
}

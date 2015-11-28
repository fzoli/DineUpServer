package com.dineup.mock;

import com.dineup.ejb.MockDatas;
import com.dineup.dom.RestaurantLocale;

public class MockRestaurantLocale implements RestaurantLocale, MockDatas {

    private final int id;
    
    public MockRestaurantLocale(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return String.format("Restaurant name %s", id);
    }

    @Override
    public String getDescription() {
        return String.format("Restaurant description %s", id);
    }

    @Override
    public String getOpenHours() {
        return DAYS_ENGLISH[id % DAYS_ENGLISH.length] + " 24h";
    }

    @Override
    public String getLanguageCode() {
        return "en";
    }
    
}

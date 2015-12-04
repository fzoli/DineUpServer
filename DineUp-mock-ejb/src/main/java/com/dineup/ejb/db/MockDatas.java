package com.dineup.ejb.db;

public interface MockDatas {
    
    int NUMBER_OF_RESTAURANTS = 200;
    int NUMBER_OF_CATEGORIES = 20;
    int NUMBER_OF_FOODS = 40;
    int NUMBER_OF_EXTRAS = 10;
    int NUMBER_OF_OPTIONS = 10;
    
    String CURRENCY = "HUF";
    String IMAGE_URL_FORMAT = "http://dummyimage.com/600x400/000/fff.jpg&text=%s";
    
    String[] ADDRESSES = {"Cím egy", "Cím kettő", "Cím három"};
    String[] RESTAURANT_TYPES = {"RestaurantA", "RestaurantB"};
    String[] EXTRA_TYPES = {"CHOOSE_ONE", "CHOOSE_TWO"};
    String[] DAYS_ENGLISH = {"Monday", "Tuesday", "Wednesday"};
    
}

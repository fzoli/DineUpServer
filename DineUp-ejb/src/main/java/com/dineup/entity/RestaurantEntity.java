package com.dineup.entity;

import com.dineup.dom.Restaurant;

public class RestaurantEntity implements Restaurant {

    private Integer key;
    private String id;

    public RestaurantEntity() {
        key = 1;
        id = "test";
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
    
    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}

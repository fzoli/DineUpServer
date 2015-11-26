package com.dineup.dom;

import java.util.List;

public interface Restaurant {
    public Integer getId();
    public String getType();
    public String getPhotoUrl();
    public String getAddress();
    public Coordinate getCoordinate();
    public int getRating();
    public List<RestaurantLocale> getLocales();
    public List<Category> getCategories();
}

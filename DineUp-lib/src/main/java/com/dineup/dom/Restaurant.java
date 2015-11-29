package com.dineup.dom;

import java.util.List;

public interface Restaurant extends LocalizedObject<RestaurantLocale> {
    public Integer getId();
    public String getType();
    public String getPhotoUrl();
    public String getAddress();
    public String getDefaultCurrency();
    public Coordinate getCoordinate();
    public int getRating();
    public List<Category> getCategories();
}

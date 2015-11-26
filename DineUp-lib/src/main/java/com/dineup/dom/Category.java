package com.dineup.dom;

import java.util.List;

public interface Category {
    public Integer getId();
    public String getPhotoUrl();
    public List<CategoryLocale> getLocales();
    public List<Food> getFoods();
}

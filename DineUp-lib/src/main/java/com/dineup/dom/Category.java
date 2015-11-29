package com.dineup.dom;

import java.util.List;

public interface Category extends LocalizedObject<CategoryLocale> {
    public Integer getId();
    public String getPhotoUrl();
    public List<Food> getFoods();
}

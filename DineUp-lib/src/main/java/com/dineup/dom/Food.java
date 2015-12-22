package com.dineup.dom;

import java.util.List;

public interface Food extends LocalizedObject<FoodLocale> {
    public Integer getId();
    public String getPhotoUrl();
    public List<Price> getPrices();
    public List<Extra> getExtras();
    public List<Comment> getComments();
}

package com.dineup.dom;

import java.util.List;

public interface Food {
    public int getId();
    public String getPhotoUrl();
    public List<FoodLocale> getLocales();
    public List<Price> getPrices();
}

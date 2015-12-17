package com.dineup.api.dom;

import java.util.List;

public interface Food {
    public int getId();
    public String getLanguageCode();
    public String getName();
    public String getDescription();
    public String getPhotoUrl();
    public List<Price> getPrices();
    public double getRating();
}

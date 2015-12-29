package com.dineup.api.dom;

import com.dineup.common.dom.Coordinate;

public interface Restaurant {
    
    public int getId();
    
    public String getLanguageCode();
    
    public Double getDistance();

    public String getType();
    
    public String getName();
    
    public String getDescription();
    
    public String getOpenHours();
    
    public String getPhotoUrl();
    
    public String getAddress();

    public String getDefaultCurrency();
    
    public Coordinate getCoordinate();
    
    public double getRating();
    
}

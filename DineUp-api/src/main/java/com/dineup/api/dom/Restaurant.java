package com.dineup.api.dom;

public interface Restaurant {
    
    public Integer getId();
    
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

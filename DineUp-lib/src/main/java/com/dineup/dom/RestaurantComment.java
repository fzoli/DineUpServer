package com.dineup.dom;

import java.util.Date;

public interface RestaurantComment {
    public Profile getProfile();
    public boolean isProfilePublic();
    public double getRating();
    public String getMessage();
    public String getLanguageCode();
    public Date getTime();
}
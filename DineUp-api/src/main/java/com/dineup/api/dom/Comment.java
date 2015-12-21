package com.dineup.api.dom;

import java.util.Date;

public interface Comment {
    public String getLanguageCode();
    public String getMessage();
    public Profile getProfile();
    public double getRating();
    public Date getTime();
}

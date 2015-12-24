package com.dineup.dom;

import java.util.Date;

public interface Comment {
    public Integer getId();
    public Profile getProfile();
    public boolean isProfilePublic();
    public double getRating();
    public Message getMessage();
    public Date getTime();
}

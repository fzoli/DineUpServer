package com.dineup.api.dom;

import java.util.Date;

public interface Profile {
    
    public enum Type {
        FACEBOOK, GOOGLE_PLUS
    }
    
    public Date getLastSync();
    public Person getPerson();
    public String getPhotoUrl();
    public Type getType();
    
}

package com.dineup.dom;

import java.util.Date;

public interface Profile {
    
    public enum Type {
        FACEBOOK, GOOGLE_PLUS
    }
    
    public Date getLastSync();
    public String getUserId();
    public Type getType();
    public Person getPerson();
}

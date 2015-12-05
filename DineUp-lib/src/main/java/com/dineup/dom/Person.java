package com.dineup.dom;

import java.util.Date;

public interface Person {
    
    public enum Sex {
        MALE, FEMALE, OTHER;
    }
    
    public interface Name {
        public String getTitle();
        public String getFirstName();
        public String getMiddleName();
        public String getLastName();
    }
    
    public Name getName();
    public Sex getSex();
    public Date getBirthDate();
    
}

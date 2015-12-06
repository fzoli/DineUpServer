package com.dineup.dom;

import java.util.Date;

public interface Person {
    
    public enum Gender {
        MALE, FEMALE;
    }
    
    public interface Name {
        public String getFirstName();
        public String getMiddleName();
        public String getLastName();
    }
    
    public Name getName();
    public Gender getGender();
    public Date getBirthDate();
    
}

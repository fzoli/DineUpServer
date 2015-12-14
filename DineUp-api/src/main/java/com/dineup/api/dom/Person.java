package com.dineup.api.dom;

import java.util.Date;

public interface Person {
    
    public enum Gender {
        MALE, FEMALE, UNKNOWN
    }
    
    public interface Name {
        public String getFirstName();
        public String getLastName();
        public String getMiddleName();
        public String getFullName();
    }
    
    public Date getBirthDate();
    public Gender getGender();
    public Name getName();
    
}

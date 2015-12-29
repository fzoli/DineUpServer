package com.dineup.api.service.impl.v1_0.element;

import com.dineup.api.dom.Person;
import java.io.Serializable;

public class PersonNameElement implements Person.Name, Serializable {

    public String firstName;
    public String lastName;
    public String middleName;
    public String fullName;

    public PersonNameElement() {
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String getFullName() {
        return fullName;
    }
    
    @Override
    public String toString() {
        return String.format("PersonName(\"%s\")", fullName);
    }
    
}

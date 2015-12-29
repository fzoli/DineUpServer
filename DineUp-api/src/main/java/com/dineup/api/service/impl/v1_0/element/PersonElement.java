package com.dineup.api.service.impl.v1_0.element;

import com.dineup.api.dom.Person;
import java.io.Serializable;
import java.util.Date;

public class PersonElement implements Person, Serializable {

    public Date birthDate;
    public Person.Gender gender;
    public PersonNameElement name;
    
    public PersonElement() {
    }

    @Override
    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public Person.Gender getGender() {
        return gender;
    }

    @Override
    public Person.Name getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return String.format("Person(name=\"\")", name);
    }
    
}

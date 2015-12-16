package com.dineup.api.service.jaxrs.v1_0.element;

import com.dineup.api.dom.Person;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
public class PersonElement implements Person, Serializable {

    @XmlElement
    public Date birthDate;
    
    @XmlElement
    public Person.Gender gender;
    
    @XmlElement
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

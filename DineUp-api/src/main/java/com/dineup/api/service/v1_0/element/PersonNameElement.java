package com.dineup.api.service.v1_0.element;

import com.dineup.api.dom.Person;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "name")
public class PersonNameElement implements Person.Name, Serializable {
    
    @XmlElement
    public String firstName;
    
    @XmlElement
    public String lastName;
    
    @XmlElement
    public String middleName;
    
    @XmlElement
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

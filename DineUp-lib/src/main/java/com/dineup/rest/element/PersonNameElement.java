package com.dineup.rest.element;

import com.dineup.dom.Person;
import com.dineup.dom.Persons;
import com.dineup.rest.ElementConfig;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "name")
public class PersonNameElement {
    
    private ElementConfig elementConfig;
    private Person.Name name;

    public PersonNameElement(ElementConfig elementConfig, Person.Name name) {
        this.elementConfig = elementConfig;
        this.name = name;
    }

    public PersonNameElement() {
    }

    @XmlElement
    public String getFirstName() {
        return name.getFirstName();
    }

    @XmlElement
    public String getMiddleName() {
        return name.getMiddleName();
    }

    @XmlElement
    public String getLastName() {
        return name.getLastName();
    }
    
    @XmlElement
    public String getFullName() {
        return Persons.getFullName(name, elementConfig.getPreferredLanguageCode());
    }
    
}

package com.dineup.service.element;

import com.dineup.service.ElementContext;
import com.dineup.dom.Person;
import com.dineup.dom.Persons;
import com.dineup.service.ElementConfig;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "name")
public class PersonNameElement {
    
    private ElementContext elementContext;
    private ElementConfig elementConfig;
    private Person.Name name;

    public PersonNameElement(ElementContext elementContext, ElementConfig elementConfig, Person.Name name) {
        this.elementContext = elementContext;
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

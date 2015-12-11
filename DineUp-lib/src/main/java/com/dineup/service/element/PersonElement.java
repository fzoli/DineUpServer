package com.dineup.service.element;

import com.dineup.service.ElementContext;
import com.dineup.dom.Person;
import com.dineup.service.ElementConfig;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
public class PersonElement {
    
    private ElementContext elementContext;
    private ElementConfig elementConfig;
    private Person person;
    
    public PersonElement(ElementContext elementContext, ElementConfig elementConfig, Person person) {
        this.elementContext = elementContext;
        this.elementConfig = elementConfig;
        this.person = person;
    }

    public PersonElement() {
    }

    @XmlElement
    public PersonNameElement getName() {
        return new PersonNameElement(elementContext, elementConfig, person.getName());
    }
    
    @XmlElement
    public String getGender() {
        return person.getGender().name();
    }
    
    @XmlElement
    public Date getBirthDate() {
        return person.getBirthDate();
    }
    
}

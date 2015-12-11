package com.dineup.service.element.converter;

import com.dineup.service.ElementContext;
import com.dineup.dom.Person;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.PersonElement;

public class PersonElementConverter extends BaseElementConverter<Person, PersonElement> {

    public PersonElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    protected PersonElement safeConvert(Person person) {
        return new PersonElement(elementContext, elementConfig, person);
    }
    
}

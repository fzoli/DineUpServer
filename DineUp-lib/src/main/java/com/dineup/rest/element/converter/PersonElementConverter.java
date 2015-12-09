package com.dineup.rest.element.converter;

import com.dineup.rest.ElementContext;
import com.dineup.dom.Person;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.PersonElement;

public class PersonElementConverter extends BaseElementConverter<Person, PersonElement> {

    public PersonElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    protected PersonElement safeConvert(Person person) {
        return new PersonElement(elementContext, elementConfig, person);
    }
    
}

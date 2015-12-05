package com.dineup.rest.element.converter;

import com.dineup.dom.Person;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.PersonElement;

public class PersonElementConverter extends BaseElementConverter<Person, PersonElement> {

    public PersonElementConverter(ElementConfig elementConfig) {
        super(elementConfig);
    }

    @Override
    protected PersonElement safeConvert(Person person) {
        return new PersonElement(elementConfig, person);
    }
    
}

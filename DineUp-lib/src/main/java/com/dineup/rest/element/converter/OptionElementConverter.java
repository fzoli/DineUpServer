package com.dineup.rest.element.converter;

import com.dineup.dom.Option;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.OptionElement;

public class OptionElementConverter extends BaseElementConverter<Option, OptionElement> {

    public OptionElementConverter(ElementConfig elementConfig) {
        super(elementConfig);
    }

    @Override
    public OptionElement safeConvert(Option option) {
        return new OptionElement(elementConfig, option);
    }
    
}

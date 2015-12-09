package com.dineup.rest.element.converter;

import com.dineup.rest.ElementContext;
import com.dineup.dom.Option;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.OptionElement;

public class OptionElementConverter extends BaseElementConverter<Option, OptionElement> {

    public OptionElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    public OptionElement safeConvert(Option option) {
        return new OptionElement(elementContext, elementConfig, option);
    }
    
}

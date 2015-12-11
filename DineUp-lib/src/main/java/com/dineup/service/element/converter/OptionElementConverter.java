package com.dineup.service.element.converter;

import com.dineup.service.ElementContext;
import com.dineup.dom.Option;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.OptionElement;

public class OptionElementConverter extends BaseElementConverter<Option, OptionElement> {

    public OptionElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    public OptionElement safeConvert(Option option) {
        return new OptionElement(elementContext, elementConfig, option);
    }
    
}

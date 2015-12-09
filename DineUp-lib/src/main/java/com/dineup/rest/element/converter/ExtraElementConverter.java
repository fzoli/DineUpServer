package com.dineup.rest.element.converter;

import com.dineup.rest.ElementContext;
import com.dineup.dom.Extra;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.ExtraElement;

public class ExtraElementConverter extends BaseElementConverter<Extra, ExtraElement> {

    public ExtraElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    public ExtraElement safeConvert(Extra extra) {
        return new ExtraElement(elementContext, elementConfig, extra);
    }
    
}

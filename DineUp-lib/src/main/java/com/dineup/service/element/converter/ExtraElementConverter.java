package com.dineup.service.element.converter;

import com.dineup.service.ElementContext;
import com.dineup.dom.Extra;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.ExtraElement;

public class ExtraElementConverter extends BaseElementConverter<Extra, ExtraElement> {

    public ExtraElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    public ExtraElement safeConvert(Extra extra) {
        return new ExtraElement(elementContext, elementConfig, extra);
    }
    
}

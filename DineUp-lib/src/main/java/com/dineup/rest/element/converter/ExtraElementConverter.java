package com.dineup.rest.element.converter;

import com.dineup.dom.Extra;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.ExtraElement;

public class ExtraElementConverter extends BaseElementConverter<Extra, ExtraElement> {

    public ExtraElementConverter(ElementConfig elementConfig) {
        super(elementConfig);
    }

    @Override
    public ExtraElement safeConvert(Extra extra) {
        return new ExtraElement(elementConfig, extra);
    }
    
}

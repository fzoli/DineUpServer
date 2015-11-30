package com.dineup.rest.element.converter;

import com.dineup.rest.ElementConfig;
import com.dineup.util.Converter;

abstract class BaseElementConverter<In, Out> implements Converter<In, Out> {

    protected final ElementConfig elementConfig;
    
    protected BaseElementConverter(ElementConfig elementConfig) {
        this.elementConfig = elementConfig;
    }

    @Override
    public final Out convert(In obj) {
        if (obj == null) {
            return null;
        }
        return safeConvert(obj);
    }
    
    protected abstract Out safeConvert(In obj);
    
}
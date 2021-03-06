package com.dineup.service.element.converter;

import com.dineup.service.ElementContext;
import com.dineup.service.ElementConfig;
import com.dineup.util.Converter;

abstract class BaseElementConverter<In, Out> implements Converter<In, Out> {

    protected final ElementConfig elementConfig;
    protected final ElementContext elementContext;

    protected BaseElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        this.elementConfig = elementConfig;
        this.elementContext = elementContext;
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

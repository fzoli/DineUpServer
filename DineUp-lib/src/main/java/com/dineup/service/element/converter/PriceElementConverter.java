package com.dineup.service.element.converter;

import com.dineup.service.ElementContext;
import com.dineup.dom.Price;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.PriceElement;

public class PriceElementConverter extends BaseElementConverter<Price, PriceElement> {

    public PriceElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    public PriceElement safeConvert(Price price) {
        return new PriceElement(elementContext, elementConfig, price);
    }
    
}

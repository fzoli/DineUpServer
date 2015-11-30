package com.dineup.rest.element.converter;

import com.dineup.dom.Price;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.PriceElement;

public class PriceElementConverter extends BaseElementConverter<Price, PriceElement> {

    public PriceElementConverter(ElementConfig elementConfig) {
        super(elementConfig);
    }

    @Override
    public PriceElement safeConvert(Price price) {
        return new PriceElement(elementConfig, price);
    }
    
}

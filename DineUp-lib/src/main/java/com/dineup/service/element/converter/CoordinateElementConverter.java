package com.dineup.service.element.converter;

import com.dineup.common.dom.Coordinate;
import com.dineup.service.ElementContext;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.CoordinateElement;

public class CoordinateElementConverter extends BaseElementConverter<Coordinate, CoordinateElement> {

    public CoordinateElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    public CoordinateElement safeConvert(Coordinate coordinate) {
        return new CoordinateElement(elementContext, elementConfig, coordinate);
    }
    
}

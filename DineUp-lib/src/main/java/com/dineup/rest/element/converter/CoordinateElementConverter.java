package com.dineup.rest.element.converter;

import com.dineup.dom.Coordinate;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.CoordinateElement;

public class CoordinateElementConverter extends BaseElementConverter<Coordinate, CoordinateElement> {

    public CoordinateElementConverter(ElementConfig elementConfig) {
        super(elementConfig);
    }

    @Override
    public CoordinateElement safeConvert(Coordinate coordinate) {
        return new CoordinateElement(elementConfig, coordinate);
    }
    
}

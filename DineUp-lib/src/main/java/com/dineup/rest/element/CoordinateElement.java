package com.dineup.rest.element;

import com.dineup.dom.Coordinate;
import com.dineup.rest.ElementContext;
import com.dineup.rest.ElementConfig;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "coordinate")
public class CoordinateElement {
    
    private ElementContext elementContext;
    private ElementConfig elementConfig;
    private Coordinate coordinate;

    public CoordinateElement() {
    }

    public CoordinateElement(ElementContext elementContext, ElementConfig elementConfig, Coordinate coordinate) {
        this.elementContext = elementContext;
        this.elementConfig = elementConfig;
        this.coordinate = coordinate;
    }

    @XmlElement
    public double getLatitude() {
        return coordinate.getLatitude();
    }

    @XmlElement
    public double getLongitude() {
        return coordinate.getLongitude();
    }
    
}

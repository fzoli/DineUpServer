package com.dineup.service.element;

import com.dineup.common.dom.Coordinate;
import com.dineup.service.ElementContext;
import com.dineup.service.ElementConfig;
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

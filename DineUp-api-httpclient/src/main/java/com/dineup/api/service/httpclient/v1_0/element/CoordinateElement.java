package com.dineup.api.service.httpclient.v1_0.element;

import java.io.Serializable;

public class CoordinateElement implements Serializable {

    public double latitude;
    public double longitude;
    
    public CoordinateElement() {
    }
    
    @Override
    public String toString() {
        return String.format("Coordinate(%s;%s)", latitude, longitude);
    }
    
}

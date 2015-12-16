package com.dineup.api.service.httpclient.v1_0.element;

import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.Restaurant;
import java.io.Serializable;

public class RestaurantElement implements Restaurant, Serializable {

    public int id;
    public String languageCode;
    public Double distance;
    public String type;
    public String name;
    public String description;
    public String openHours;
    public String photoUrl;
    public String address;
    public String defaultCurrency;
    public CoordinateElement coordinate;
    public double rating;
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public Double getDistance() {
        return distance;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getOpenHours() {
        return openHours;
    }

    @Override
    public String getPhotoUrl() {
        return photoUrl;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    @Override
    public Coordinate getCoordinate() {
        return new Coordinate(coordinate.latitude, coordinate.longitude);
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return String.format("Restaurant(id=%s)", id);
    }
    
    public RestaurantElement() {
    }
    
}

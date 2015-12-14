package com.dineup.api;

import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.exception.DetailedException;
import java.util.List;

public interface DineUpApi {

    public Service getService() throws DetailedException;
    public List<Restaurant> getRestaurants(Coordinate coordinate) throws DetailedException;
    
}

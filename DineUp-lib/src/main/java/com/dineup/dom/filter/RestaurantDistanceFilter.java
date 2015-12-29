package com.dineup.dom.filter;

import com.dineup.util.Filter;
import com.dineup.common.dom.Area;
import com.dineup.dom.Restaurant;

public class RestaurantDistanceFilter implements Filter<Restaurant> {
    
    private final Area area;

    public RestaurantDistanceFilter(Area area) {
        this.area = area;
    }

    @Override
    public boolean isFiltered(Restaurant restaurant) {
        if (area == null) {
            return false;
        }
        boolean inArea = area.isInArea(restaurant.getCoordinate());
        return !inArea;
    }
    
}

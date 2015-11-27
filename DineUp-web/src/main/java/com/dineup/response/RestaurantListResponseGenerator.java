package com.dineup.response;

import com.dineup.dom.Restaurant;
import com.dineup.ejb.RestaurantDataSource;

import java.util.List;

public class RestaurantListResponseGenerator implements ResponseGenerator<List<Restaurant>> {

    private final RestaurantDataSource dataSource;

    public RestaurantListResponseGenerator(RestaurantDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Restaurant> generateResponse() {
        return dataSource.getRestaurants();
    }

}

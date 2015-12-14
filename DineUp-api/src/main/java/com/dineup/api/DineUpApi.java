package com.dineup.api;

import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.exception.DetailedException;
import com.dineup.api.service.DineUpApiHandler;
import java.util.List;
import javax.ws.rs.client.Client;

public class DineUpApi {

    private final DineUpApiHandler handler;
    
    public DineUpApi(Client client, TargetConfig targetConfig) {
        handler = new DineUpApiHandler(client, targetConfig);
    }
    
    public List<Restaurant> getRestaurants(final Coordinate coordinate) throws DetailedException {
        return handler.getRestaurants(coordinate);
    }
    
}

package com.dineup.api.service;

import com.dineup.api.DineUpApi;
import com.dineup.api.TargetConfig;
import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.exception.DetailedException;
import com.dineup.api.service.element.RestaurantElement;
import com.dineup.service.rest.ElementConfigKeys;
import com.dineup.service.rest.RequestPath;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class DineUpApiHandler implements DineUpApi {

    private final Executor executor;
    
    public DineUpApiHandler(Client client, TargetConfig targetConfig) {
        this.executor = new Executor(client, targetConfig);
    }
    
    @Override
    public List<Restaurant> getRestaurants(final Coordinate coordinate) throws DetailedException {
        return executor.execute(new GetRestaurants(coordinate));
    }
    
    private static class GetRestaurants extends Executable<List<Restaurant>> {

        private final Coordinate coordinate;
        
        public GetRestaurants(Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        @Override
        public WebTarget appendPath(WebTarget target) {
            return target.path(RequestPath.PATH_RESTAURANTS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            if (coordinate != null) {
                parameters.put(ElementConfigKeys.LATITUDE, coordinate.getLatitude());
                parameters.put(ElementConfigKeys.LONGITUDE, coordinate.getLongitude());
            }
        }

        @Override
        public List<Restaurant> parseResponse(Response response) {
            GenericType<List<RestaurantElement>> type = new GenericType<List<RestaurantElement>>(){};
            List<RestaurantElement> restaurants = response.readEntity(type);
            return Collections.unmodifiableList((List) restaurants);
        }
        
    }
    
}

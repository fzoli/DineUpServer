package com.dineup.api.service;

import com.dineup.api.TargetConfig;
import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.service.element.RestaurantElement;
import com.dineup.service.rest.ElementConfigKeys;
import com.dineup.service.rest.RequestPath;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class DineUpApiHandler {

    private final Executor executor;
    
    public DineUpApiHandler(Client client, TargetConfig targetConfig) {
        this.executor = new Executor(client, targetConfig);
    }
    
    public List<Restaurant> getRestaurants(final Coordinate coordinate) throws Exception {
        return executor.execute(new GetRestaurants(coordinate));
    }
    
    private class GetRestaurants extends Executable<List<Restaurant>> {

        private final Coordinate coordinate;
        
        public GetRestaurants(Coordinate coordinate) {
            this.coordinate = coordinate;
        }
        
        @Override
        public WebTarget createTarget(Client client, TargetConfig targetConfig) {
            WebTarget target = client.target(targetConfig.getTarget())
                .path(RequestPath.ROOT_JSON)
                .path(RequestPath.PATH_RESTAURANTS)
                .queryParam(ElementConfigKeys.LANGUAGE_CODE, targetConfig.getLanguageCode());
            if (coordinate != null) {
                target = target
                    .queryParam(ElementConfigKeys.LATITUDE, coordinate.getLatitude())
                    .queryParam(ElementConfigKeys.LONGITUDE, coordinate.getLongitude());
            }
            return target;
        }

        @Override
        public List<Restaurant> parseResponse(Response response) {
            GenericType<List<RestaurantElement>> type = new GenericType<List<RestaurantElement>>(){};
            List<RestaurantElement> restaurants = response.readEntity(type);
            return Collections.unmodifiableList((List) restaurants);
        }
        
    }
    
}

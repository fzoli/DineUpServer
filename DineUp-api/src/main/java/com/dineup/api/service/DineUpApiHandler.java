package com.dineup.api.service;

import com.dineup.api.DineUpApi;
import com.dineup.api.Service;
import com.dineup.api.TargetConfig;
import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.ProfileToken;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.dom.RestaurantComment;
import com.dineup.api.exception.DetailedException;
import com.dineup.api.service.element.RestaurantCommentElement;
import com.dineup.api.service.element.RestaurantElement;
import com.dineup.api.service.element.ServiceElement;
import com.dineup.service.rest.ElementConfigKeys;
import com.dineup.service.rest.RequestPath;
import com.dineup.service.rest.RestaurantKeys;
import com.sun.istack.internal.Nullable;
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
    public Service getService() throws DetailedException {
        return executor.execute(new GetService());
    }
    
    @Override
    public List<Restaurant> getRestaurants(final Coordinate coordinate) throws DetailedException {
        return executor.execute(new GetRestaurants(coordinate));
    }

    @Override
    public List<RestaurantComment> getRestaurantComments(Restaurant restaurant, @Nullable ProfileToken profileToken) throws DetailedException {
        return executor.execute(new GetRestaurantComments(restaurant.getId(), profileToken));
    }
    
    private static class GetService extends Executable<Service> {

        public GetService() {
        }

        @Override
        public WebTarget appendPath(WebTarget target) {
            return target.path(RequestPath.PATH_SERVICE);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
        }

        @Override
        public Service parseResponse(Response response) {
            GenericType<ServiceElement> type = new GenericType<ServiceElement>(){};
            ServiceElement entity = response.readEntity(type);
            return entity;
        }
        
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
            List<RestaurantElement> entity = response.readEntity(type);
            return Collections.unmodifiableList((List) entity);
        }
        
    }
    
    private static class GetRestaurantComments extends Executable<List<RestaurantComment>> {

        private final int restaurantId;
        private final ProfileToken profileToken;
        
        public GetRestaurantComments(int restaurantId, ProfileToken profileToken) {
            this.restaurantId = restaurantId;
            this.profileToken = profileToken;
        }

        @Override
        public WebTarget appendPath(WebTarget target) {
            return target.path(RequestPath.PATH_RESTAURANT_COMMENTS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.RESTAURANT_ID, restaurantId);
            if (profileToken != null) {
                switch (profileToken.getType()) {
                    case FACEBOOK:
                        parameters.put(ElementConfigKeys.FACEBOOK_ACCESS_TOKEN, profileToken.getAccessToken());
                        break;
                    case GOOGLE_PLUS:
                        parameters.put(ElementConfigKeys.GOOGLE_ACCESS_TOKEN, profileToken.getAccessToken());
                        break;
                }
            }
        }

        @Override
        public List<RestaurantComment> parseResponse(Response response) {
            GenericType<List<RestaurantCommentElement>> type = new GenericType<List<RestaurantCommentElement>>(){};
            List<RestaurantCommentElement> entity = response.readEntity(type);
            return Collections.unmodifiableList((List) entity);
        }
        
    }
    
}

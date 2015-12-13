package com.dineup.api;

import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.element.RestaurantElement;
import com.dineup.service.rest.ElementConfigKeys;
import com.dineup.service.rest.RequestPath;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringEscapeUtils;

public class DineUpApi {

    private final Client client;
    private final TargetConfig targetConfig;
    
    public DineUpApi(Client client, TargetConfig targetConfig) {
        if (client == null) {
            throw new IllegalArgumentException("client is null");
        }
        if (targetConfig == null) {
            throw new IllegalArgumentException("targetConfig is null");
        }
        this.client = client;
        this.targetConfig = targetConfig;
    }
    
    // TODO: split the method by comments
    public List<Restaurant> getRestaurants(Coordinate coordinate) throws Exception {
        // Build request
        WebTarget target = client.target(targetConfig.getTarget())
            .path(RequestPath.ROOT_JSON)
            .path(RequestPath.PATH_RESTAURANTS)
            .queryParam(ElementConfigKeys.LANGUAGE_CODE, targetConfig.getLanguageCode());
        if (coordinate != null) {
            target = target
                .queryParam(ElementConfigKeys.LATITUDE, coordinate.getLatitude())
                .queryParam(ElementConfigKeys.LONGITUDE, coordinate.getLongitude());
        }
        
        // Execute request
        Response restaurantResponse = target
                .request()
                .get(); // TODO: try-catch and throw localized exception (network error etc.)
        
        // Handle server-side error
        if (Response.Status.OK.getStatusCode() != restaurantResponse.getStatus()) {
            String errorType = restaurantResponse.getHeaderString("Error");
            System.out.println("ERROR " + restaurantResponse.getStatus() + (errorType == null ? "" : " "+errorType));
            if (errorType != null) {
                System.out.println("ErrorMessage: "+StringEscapeUtils.unescapeJson(restaurantResponse.getHeaderString("ErrorMessage")));
                System.out.println("ErrorDescription: "+StringEscapeUtils.unescapeJson(restaurantResponse.getHeaderString("ErrorDescription")));
                
                System.out.println("LocalizedErrorMessage: "+StringEscapeUtils.unescapeJson(restaurantResponse.getHeaderString("LocalizedErrorMessage")));
                System.out.println("LocalizedErrorDescription: "+StringEscapeUtils.unescapeJson(restaurantResponse.getHeaderString("LocalizedErrorDescription")));
            }
            return null; // TODO: throw localized exception
        }
        
        // Parse response
        GenericType<List<RestaurantElement>> type = new GenericType<List<RestaurantElement>>(){};
        List<RestaurantElement> restaurants = restaurantResponse.readEntity(type);
        return Collections.unmodifiableList((List) restaurants);
    }
    
}

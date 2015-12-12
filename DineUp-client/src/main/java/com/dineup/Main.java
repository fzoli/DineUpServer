package com.dineup;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringEscapeUtils;

public class Main {
    
    private static final String SERVER_URL = "http://localhost:8080/";
    private static final String WEBAPP_ROOT = "/DineUp";
    private static final String REST_ROOT = "/rest";
    
    private static final String PATH_PREFIX = "/json";
    private static final String PATH_RESTAURANTS = "/restaurants";
    
    private static final String PARAM_LANGUAGE = "language";
    private static final String PARAM_LATITUDE = "latitude";
    private static final String PARAM_LONGITUDE = "longitude";
    
    public static void main(String[] args) throws Exception {
        Client client = ClientBuilder.newClient();
        TargetConfig targetConfig = TargetConfig.newBuilder()
                .serverUrl(SERVER_URL)
                .webAppRoot(WEBAPP_ROOT)
                .restRoot(REST_ROOT)
                .build();
        
        WebTarget target = client.target(targetConfig.getTarget())
                .path(PATH_PREFIX)
                .path(PATH_RESTAURANTS)
                .queryParam(PARAM_LANGUAGE, "hu")
                .queryParam(PARAM_LATITUDE, 5.6)
                .queryParam(PARAM_LONGITUDE, 5.5);
        
        System.out.println(target.getUri());
        
        Response restaurantResponse = target
                .request()
                .get();
        
        if (Response.Status.OK.getStatusCode() != restaurantResponse.getStatus()) {
            String errorType = restaurantResponse.getHeaderString("Error");
            System.out.println("ERROR " + restaurantResponse.getStatus() + (errorType == null ? "" : " "+errorType));
            if (errorType != null) {
                System.out.println("ErrorMessage: "+StringEscapeUtils.unescapeJson(restaurantResponse.getHeaderString("ErrorMessage")));
                System.out.println("ErrorDescription: "+StringEscapeUtils.unescapeJson(restaurantResponse.getHeaderString("ErrorDescription")));
                
                System.out.println("LocalizedErrorMessage: "+StringEscapeUtils.unescapeJson(restaurantResponse.getHeaderString("LocalizedErrorMessage")));
                System.out.println("LocalizedErrorDescription: "+StringEscapeUtils.unescapeJson(restaurantResponse.getHeaderString("LocalizedErrorDescription")));
            }
            return;
        }
        
        GenericType<List<Restaurant>> type = new GenericType<List<Restaurant>>(){};
        List<Restaurant> restaurants = restaurantResponse.readEntity(type);
        System.out.println(restaurants);
    }
    
}

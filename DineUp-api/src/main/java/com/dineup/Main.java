package com.dineup;

import com.dineup.api.DineUpApi;
import com.dineup.api.TargetConfig;
import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.Restaurant;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Main {
    
    public static void main(String[] args) throws Exception {
        Client client = ClientBuilder.newClient();
        TargetConfig targetConfig = TargetConfig.newBuilder()
                .serverUrl("http://localhost:8080/")
                .webAppRoot("/DineUp")
                .restRoot("/rest")
                .languageCode("hu")
                .build();
        DineUpApi api = new DineUpApi(client, targetConfig);
        List<Restaurant> restaurants = api.getRestaurants(new Coordinate(5, 6));
        System.out.println("Number of restaurants: " + restaurants.size());
    }
    
}

package com.dineup;

import com.dineup.api.DineUpApi;
import com.dineup.api.DineUpApiFactory;
import com.dineup.api.Service;
import com.dineup.api.TargetConfig;
import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.exception.DetailedException;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Example.class);
    
    private DineUpApi api;
    
    public Example() {
        Client client = ClientBuilder.newClient();
        TargetConfig targetConfig = TargetConfig.newBuilder()
                .serverUrl("http://localhost:8080/")
                .webAppRoot("/DineUp")
                .restRoot("/rest")
                .languageCode("hu")
                .build();
        api = DineUpApiFactory.createInstance(client, targetConfig);
    }
    
    public void hello() {
        try {
            Service service = api.getService();
            if (!service.isUpToDate()) {
                LOGGER.info("Please upgrade the client.");
                return;
            }
            List<Restaurant> restaurants = api.getRestaurants(new Coordinate(5, 6));
            LOGGER.info("Number of restaurants: " + restaurants.size());
        }
        catch (DetailedException ex) {
            LOGGER.error("Error: " + ex, ex);
        }
    }
    
    public static void main(String[] args) {
        new Example().hello();
    }
    
}

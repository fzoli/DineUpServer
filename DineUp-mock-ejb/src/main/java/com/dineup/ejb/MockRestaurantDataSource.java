package com.dineup.ejb;

import com.dineup.dom.Restaurant;
import java.util.Collections;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class MockRestaurantDataSource implements RestaurantDataSource {
    
    @Override
    public List<Restaurant> getRestaurants() {
        return Collections.emptyList();
    }

}

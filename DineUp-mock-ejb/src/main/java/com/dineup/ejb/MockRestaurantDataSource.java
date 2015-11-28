package com.dineup.ejb;

import com.dineup.dom.*;
import com.dineup.mock.MockRestaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class MockRestaurantDataSource implements RestaurantDataSource, MockDatas {

    public MockRestaurantDataSource() {
    }

    @Override
    public List<Restaurant> getRestaurants() {
        List<Restaurant> list = new ArrayList<>(NUMBER_OF_RESTAURANTS);
        for (int i = 0; i < NUMBER_OF_RESTAURANTS; i++) {
            list.add(new MockRestaurant(i));
        }
        return Collections.unmodifiableList(list);
    }

}

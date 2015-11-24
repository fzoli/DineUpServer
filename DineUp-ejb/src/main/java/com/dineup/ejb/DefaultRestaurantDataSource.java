package com.dineup.ejb;

import com.dineup.dom.Restaurant;
import com.dineup.entity.RestaurantEntity;
import java.util.Collections;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class DefaultRestaurantDataSource implements RestaurantDataSource {

    @Override
    public List<Restaurant> getRestaurants() {
        return (List) Collections.singletonList(new RestaurantEntity());
    }

}

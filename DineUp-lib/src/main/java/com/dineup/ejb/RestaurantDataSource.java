package com.dineup.ejb;

import com.dineup.dom.Restaurant;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RestaurantDataSource {
    public List<Restaurant> getRestaurants();
}

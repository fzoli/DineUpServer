package com.dineup.api;

import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.ProfileToken;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.dom.RestaurantComment;
import com.dineup.api.exception.DetailedException;
import com.sun.istack.internal.Nullable;
import java.util.List;

public interface DineUpApi {

    public Service getService() throws DetailedException;
    public List<Restaurant> getRestaurants(Coordinate coordinate) throws DetailedException;
    public List<RestaurantComment> getRestaurantComments(Restaurant restaurant, @Nullable ProfileToken profileToken) throws DetailedException;
    
}

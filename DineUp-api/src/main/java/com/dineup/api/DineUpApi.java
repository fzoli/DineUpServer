package com.dineup.api;

import com.dineup.api.dom.Service;
import com.dineup.api.annotation.Nullable;
import com.dineup.api.dom.Category;
import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.Extra;
import com.dineup.api.dom.Food;
import com.dineup.api.dom.Option;
import com.dineup.api.dom.ProfileToken;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.dom.RestaurantComment;
import com.dineup.api.exception.DetailedException;
import java.util.List;

public interface DineUpApi {

    public Service getService() throws DetailedException;
    public List<Restaurant> getRestaurants(Coordinate coordinate) throws DetailedException;
    public List<RestaurantComment> getRestaurantComments(Restaurant restaurant, @Nullable ProfileToken profileToken) throws DetailedException;
    public List<Category> getCategories(Restaurant restaurant) throws DetailedException;
    public List<Food> getFoods(Category category) throws DetailedException;
    public List<Extra> getExtras(Food food) throws DetailedException;
    public List<Option> getOptions(Extra extra) throws DetailedException;
    
}

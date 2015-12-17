package com.dineup.api;

import java.util.List;

import com.dineup.api.annotation.Nullable;
import com.dineup.api.dom.Category;
import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.Event;
import com.dineup.api.dom.Extra;
import com.dineup.api.dom.Food;
import com.dineup.api.dom.FoodCommentRequest;
import com.dineup.api.dom.Join;
import com.dineup.api.dom.Option;
import com.dineup.api.dom.Order;
import com.dineup.api.dom.OrderRequest;
import com.dineup.api.dom.ProfileToken;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.dom.RestaurantComment;
import com.dineup.api.dom.RestaurantCommentRequest;
import com.dineup.api.dom.Service;
import com.dineup.api.exception.DetailedException;

public interface DineUpApi {

    public Service getService() throws DetailedException;
    public List<Restaurant> getRestaurants(@Nullable Coordinate coordinate) throws DetailedException;
    public List<RestaurantComment> getRestaurantComments(Restaurant restaurant, @Nullable ProfileToken profileToken) throws DetailedException;
    public List<Category> getCategories(Restaurant restaurant) throws DetailedException;
    public List<Food> getFoods(Category category) throws DetailedException;
    public List<Extra> getExtras(Food food) throws DetailedException;
    public List<Option> getOptions(Extra extra) throws DetailedException;
    
    public void sendRestaurantComment(RestaurantCommentRequest restaurantCommentRequest, ProfileToken profileToken) throws DetailedException;
    public void sendFoodComment(FoodCommentRequest foodCommentRequest, ProfileToken profileToken) throws DetailedException;        
    
    public void sendOrder(OrderRequest orderRequest, ProfileToken profileToken) throws DetailedException;
    public List<Order> getOrders(ProfileToken profileToken) throws DetailedException;
        
    
    public void addEvent(Event event) throws DetailedException;
    public List<Event> getEvents() throws DetailedException;
    public List<Event> getMyEvents() throws DetailedException;
    public void joinEvent(Event event, ProfileToken profileToken) throws DetailedException;
    public List<Join> getJoinRequests(Event event, ProfileToken profileToken) throws DetailedException;
    public void acceptJoin(Event event, Join join, ProfileToken profileToken) throws DetailedException;
    
}

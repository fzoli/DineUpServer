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
import com.dineup.api.dom.Range;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.dom.RestaurantComment;
import com.dineup.api.dom.RestaurantCommentRequest;
import com.dineup.api.dom.Service;
import com.dineup.api.exception.DetailedException;

public interface DineUpApi {

    public Service getService() throws DetailedException;
    public List<Restaurant> getRestaurants(@Nullable Coordinate coordinate) throws DetailedException; //TODO: filter on/off
    public List<RestaurantComment> getRestaurantComments(Restaurant restaurant, @Nullable ProfileToken profileToken) throws DetailedException;
    public List<Category> getCategories(Restaurant restaurant) throws DetailedException;
    public List<Food> getFoods(Category category) throws DetailedException;
    public List<Extra> getExtras(Food food) throws DetailedException;
    public List<Option> getOptions(Extra extra) throws DetailedException;
    
    public void sendRestaurantComment(RestaurantCommentRequest restaurantCommentRequest, ProfileToken profileToken) throws DetailedException;
    public void sendFoodComment(FoodCommentRequest foodCommentRequest, ProfileToken profileToken) throws DetailedException;        
    
    public void sendOrder(OrderRequest orderRequest, ProfileToken profileToken) throws DetailedException;
    public List<Order> getOrders(ProfileToken profileToken) throws DetailedException;
    
    public void addEvent(EventRequest event, ProfileToken profileToken) throws DetailedException;
    public void deleteEvent(EventRequest event, ProfileToken profileToken) throws DetailedException;
    public List<Event> getEvents(@Nullable Restaurant restaurant, @Nullable Range range) throws DetailedException; //TODO: filtering refactor
    public List<Event> getEvents(@Nullable Coordinate coordinate, @Nullable Range range) throws DetailedException; //TODO: filtering refactor
    public List<Event> getCreatedEvents(ProfileToken profileToken) throws DetailedException;
    public void joinEvent(Event event, ProfileToken profileToken) throws DetailedException;
    public void leaveEvent(Event event, ProfileToken profileToken) throws DetailedException;
    public void acceptJoin(Join join, ProfileToken profileToken) throws DetailedException;
    public void deleteJoin(Join join, ProfileToken profileToken) throws DetailedException;
    
}

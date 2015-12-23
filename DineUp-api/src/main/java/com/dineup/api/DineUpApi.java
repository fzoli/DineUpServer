package com.dineup.api;

import java.util.List;

import com.dineup.api.annotation.Nullable;
import com.dineup.api.dom.Category;
import com.dineup.api.dom.Extra;
import com.dineup.api.dom.Food;
import com.dineup.api.request.FoodCommentRequest;
import com.dineup.api.dom.Option;
import com.dineup.api.dom.ProfileToken;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.request.RestaurantCommentRequest;
import com.dineup.api.dom.Service;
import com.dineup.api.exception.DetailedException;
import com.dineup.api.dom.Comment;
import com.dineup.api.request.query.RestaurantQuery;

public interface DineUpApi {

    public Service getService() throws DetailedException;
    
    public List<Restaurant> getRestaurants(@Nullable RestaurantQuery query) throws DetailedException;
    public List<Category> getCategories(Restaurant restaurant) throws DetailedException;
    public List<Food> getFoods(Category category) throws DetailedException;
    public List<Extra> getExtras(Food food) throws DetailedException;
    public List<Option> getOptions(Extra extra) throws DetailedException;
    
    public List<Comment> getRestaurantComments(Restaurant restaurant, @Nullable ProfileToken profileToken) throws DetailedException;
    public List<Comment> getFoodComments(Food food, @Nullable ProfileToken profileToken) throws DetailedException;
    
    public int addRestaurantComment(RestaurantCommentRequest restaurantCommentRequest, ProfileToken profileToken) throws DetailedException;
    public int addFoodComment(FoodCommentRequest foodCommentRequest, ProfileToken profileToken) throws DetailedException;        
    
    /*
    public List<Order> getOrders(ProfileToken profileToken) throws DetailedException;
    public void sendOrder(OrderRequest orderRequest, ProfileToken profileToken) throws DetailedException;
    
    
    public List<Event> getEvents(@Nullable Restaurant restaurant, @Nullable TimeInterval interval) throws DetailedException; //TODO: filtering refactor
    public List<Event> getEvents(@Nullable Coordinate coordinate, @Nullable TimeInterval interval) throws DetailedException; //TODO: filtering refactor
    public List<Event> getCreatedEvents(ProfileToken profileToken) throws DetailedException;
    
    public void addEvent(EventRequest event, ProfileToken profileToken) throws DetailedException;
    public void deleteEvent(Event event, ProfileToken profileToken) throws DetailedException;
    
    public void acceptJoin(Join join, ProfileToken profileToken) throws DetailedException;
    public void deleteJoin(Join join, ProfileToken profileToken) throws DetailedException;
    
    public void joinEvent(Event event, ProfileToken profileToken) throws DetailedException;
    public void leaveEvent(Event event, ProfileToken profileToken) throws DetailedException;
    */
}

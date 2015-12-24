package com.dineup.ejb.db;

import com.dineup.dom.*;
import com.dineup.ejb.db.data.FoodCommentData;
import com.dineup.ejb.db.data.RestaurantCommentData;
import com.dineup.ejb.profile.ProfileManagerFactory;
import com.dineup.mock.*;
import com.dineup.util.Filter;
import com.dineup.util.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;

@Singleton
public class MockRestaurantDataSource implements RestaurantDataSource, MockDatas {

    @EJB
    private ProfileManagerFactory profileManagerFactory;
    
    public MockRestaurantDataSource() {
    }

    public Profile getProfile(int profileId) {
        return new MockProfile(this, profileManagerFactory, profileId);
    }
    
    @Override
    public Restaurant getRestaurant(int restaurantId) {
        return new MockRestaurant(this, restaurantId);
    }
    
    @Override
    public List<Restaurant> getRestaurants(Filter<Restaurant> ... filters) {
        List<Restaurant> list = new ArrayList<>(NUMBER_OF_RESTAURANTS);
        for (int i = 0; i < NUMBER_OF_RESTAURANTS; i++) {
            list.add(new MockRestaurant(this, i));
        }
        Lists.filter(list, filters);
        return Collections.unmodifiableList(list);
    }

    @Override
    public List<Comment> getRestaurantComments(int restaurantId) {
        List<Comment> list = new ArrayList<>(NUMBER_OF_RESTAURANT_COMMENTS);
        for (int i = 0; i < NUMBER_OF_RESTAURANT_COMMENTS; i++) {
            list.add(new MockComment(this, profileManagerFactory, i));
        }
        return Collections.unmodifiableList(list);
    }
    
    @Override
    public List<Category> getCategories(int restaurantId) {
        List<Category> list = new ArrayList<>(NUMBER_OF_CATEGORIES);
        for (int i = 0; i < NUMBER_OF_CATEGORIES; i++) {
            list.add(new MockCategory(this, i));
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public Food getFood(int foodId) {
        return new MockFood(this, foodId);
    }
    
    @Override
    public List<Food> getFoods(int categoryId) {
        List<Food> list = new ArrayList<>(NUMBER_OF_FOODS);
        for (int i = 0; i < NUMBER_OF_FOODS; i++) {
            list.add(new MockFood(this, i));
        }
        return Collections.unmodifiableList(list);
    }
    
    @Override
    public List<Comment> getFoodComments(int foodId) {
        List<Comment> list = new ArrayList<>(NUMBER_OF_FOOD_COMMENTS);
        for (int i = 0; i < NUMBER_OF_FOOD_COMMENTS; i++) {
            list.add(new MockComment(this, profileManagerFactory, i));
        }
        return Collections.unmodifiableList(list);
    }
    
    @Override
    public List<Extra> getExtras(int foodId) {
        List<Extra> list = new ArrayList<>(NUMBER_OF_EXTRAS);
        for (int i = 0; i < NUMBER_OF_FOODS; i++) {
            list.add(new MockExtra(this, i));
        }
        return Collections.unmodifiableList(list);
    }
    
    @Override
    public List<Option> getOptions(int extraId) {
        List<Option> list = new ArrayList<>(NUMBER_OF_OPTIONS);
        for (int i = 0; i < NUMBER_OF_OPTIONS; i++) {
            list.add(new MockOption(this, i));
        }
        return Collections.unmodifiableList(list);
    }
    
    @Override
    public int addRestaurantComment(RestaurantCommentData commentData) {
        return 0;
    }
    
    @Override
    public int addFoodComment(FoodCommentData commentData) {
        return 0;
    }

}

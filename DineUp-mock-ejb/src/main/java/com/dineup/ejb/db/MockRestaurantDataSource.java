package com.dineup.ejb.db;

import com.dineup.dom.*;
import com.dineup.ejb.profile.ProfileManagerFactory;
import com.dineup.mock.*;

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

    @Override
    public List<Restaurant> getRestaurants() {
        List<Restaurant> list = new ArrayList<>(NUMBER_OF_RESTAURANTS);
        for (int i = 0; i < NUMBER_OF_RESTAURANTS; i++) {
            list.add(new MockRestaurant(this, i));
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public List<RestaurantComment> getRestaurantComments(int restaurantId) {
        List<RestaurantComment> list = new ArrayList<>(NUMBER_OF_RESTAURANT_COMMENTS);
        for (int i = 0; i < NUMBER_OF_RESTAURANT_COMMENTS; i++) {
            list.add(new MockRestaurantComment(this, profileManagerFactory, i));
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
    public List<Food> getFoods(int categoryId) {
        List<Food> list = new ArrayList<>(NUMBER_OF_FOODS);
        for (int i = 0; i < NUMBER_OF_FOODS; i++) {
            list.add(new MockFood(this, i));
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
    
}

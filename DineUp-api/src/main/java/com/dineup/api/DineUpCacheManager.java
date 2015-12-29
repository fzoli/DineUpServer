package com.dineup.api;

import com.dineup.api.annotation.Nullable;
import com.dineup.api.dom.Category;
import com.dineup.api.dom.Comment;
import com.dineup.api.dom.Extra;
import com.dineup.api.dom.Food;
import com.dineup.api.dom.Option;
import com.dineup.api.dom.ProfileToken;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.dom.Service;
import java.util.List;

public interface DineUpCacheManager {
    public void invalidate();
    public DineUpCache<Service> getService();
    public DineUpCache<List<Restaurant>> getRestaurants();
    public DineUpCache<List<Category>> getCategories(Restaurant restaurant);
    public DineUpCache<List<Food>> getFoods(Category category);
    public DineUpCache<List<Extra>> getExtras(Food food);
    public DineUpCache<List<Option>> getOptions(Extra extra);
    public DineUpCache<List<Comment>> getRestaurantComments(Restaurant restaurant, @Nullable ProfileToken token);
    public DineUpCache<List<Comment>> getFoodComments(Food food, @Nullable ProfileToken token);
}

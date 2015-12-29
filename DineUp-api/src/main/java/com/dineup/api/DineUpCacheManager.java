package com.dineup.api;

import com.dineup.api.dom.Category;
import com.dineup.api.dom.Extra;
import com.dineup.api.dom.Food;
import com.dineup.api.dom.Option;
import com.dineup.api.dom.Restaurant;
import java.util.List;

public interface DineUpCacheManager {
    
    public DineUpCache<List<Restaurant>> getRestaurants();
    public DineUpCache<List<Category>> getCategories(Restaurant restaurant);
    public DineUpCache<List<Food>> getFoods(Category category);
    public DineUpCache<List<Extra>> getExtras(Food food);
    public DineUpCache<List<Option>> getOptions(Extra extra);
    
}

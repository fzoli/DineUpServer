package com.dineup.api.service.impl;

import com.dineup.api.DineUpCache;
import com.dineup.api.DineUpCacheManager;
import com.dineup.api.dom.Category;
import com.dineup.api.dom.Extra;
import com.dineup.api.dom.Food;
import com.dineup.api.dom.Option;
import com.dineup.api.dom.Restaurant;
import java.util.List;

public class DineUpNoCacheManager implements DineUpCacheManager {

    @Override
    public DineUpCache<List<Restaurant>> getRestaurants() {
        return noCache();
    }

    @Override
    public DineUpCache<List<Category>> getCategories(Restaurant restaurant) {
        return noCache();
    }

    @Override
    public DineUpCache<List<Food>> getFoods(Category category) {
        return noCache();
    }

    @Override
    public DineUpCache<List<Extra>> getExtras(Food food) {
        return noCache();
    }

    @Override
    public DineUpCache<List<Option>> getOptions(Extra extra) {
        return noCache();
    }
    
    private <T> DineUpCache<T> noCache() {
        return new DineUpCache<T>() {

            @Override
            public DineUpCache.CacheResult<T> get() throws Exception {
                return null;
            }
            
            @Override
            public void set(T data, String apiVersion, String languageCode) throws Exception {
                
            }

        };
    }
    
}

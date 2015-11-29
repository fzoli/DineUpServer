package com.dineup.mock;

import com.dineup.ejb.MockDatas;
import com.dineup.dom.Category;
import com.dineup.dom.CategoryLocale;
import com.dineup.dom.Food;
import com.dineup.ejb.RestaurantDataSource;
import java.util.Collections;
import java.util.List;

public class MockCategory implements Category, MockDatas {

    private final RestaurantDataSource dataSource;
    private final int id;
    
    public MockCategory(RestaurantDataSource dataSource, int id) {
        this.dataSource = dataSource;
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getPhotoUrl() {
        return String.format(IMAGE_URL_FORMAT, "Category" + id);
    }

    @Override
    public List<CategoryLocale> getLocales() {
        return (List)Collections.singletonList(new MockCategoryLocale(id));
    }

    @Override
    public List<Food> getFoods() {
        return dataSource.getFoods(id);
    }
    
}

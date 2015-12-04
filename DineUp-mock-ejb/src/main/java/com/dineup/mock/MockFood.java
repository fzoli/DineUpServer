package com.dineup.mock;

import com.dineup.ejb.db.MockDatas;
import com.dineup.dom.Extra;
import com.dineup.dom.Food;
import com.dineup.dom.FoodLocale;
import com.dineup.dom.Price;
import com.dineup.ejb.db.RestaurantDataSource;
import java.util.Collections;
import java.util.List;

public class MockFood implements Food, MockDatas {

    private final RestaurantDataSource dataSource;
    private final int id;
    
    public MockFood(RestaurantDataSource dataSource, int id) {
        this.dataSource = dataSource;
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getPhotoUrl() {
        return String.format(IMAGE_URL_FORMAT, "Food" + id);
    }

    @Override
    public List<FoodLocale> getLocales() {
        return (List) Collections.singletonList(new MockFoodLocale(id));
    }

    @Override
    public List<Price> getPrices() {
        return (List) Collections.singletonList(new MockPrice());
    }

    @Override
    public List<Extra> getExtras() {
        return dataSource.getExtras(id);
    }
    
}

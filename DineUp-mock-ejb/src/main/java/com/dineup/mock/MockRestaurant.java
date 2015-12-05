package com.dineup.mock;

import com.dineup.ejb.db.MockDatas;
import com.dineup.dom.Category;
import com.dineup.dom.Coordinate;
import com.dineup.dom.Restaurant;
import com.dineup.dom.RestaurantComment;
import com.dineup.dom.RestaurantLocale;
import com.dineup.ejb.db.RestaurantDataSource;
import java.util.Collections;
import java.util.List;

public class MockRestaurant implements Restaurant, MockDatas {
    
    private final RestaurantDataSource dataSource;
    private final int id;
    
    public MockRestaurant(RestaurantDataSource dataSource, int id) {
        this.id = id;
        this.dataSource = dataSource;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getType() {
        return RESTAURANT_TYPES[id % RESTAURANT_TYPES.length];
    }

    @Override
    public String getPhotoUrl() {
        return String.format(IMAGE_URL_FORMAT, "Restaurant"+id);
    }

    @Override
    public String getAddress() {
        return ADDRESSES[id % ADDRESSES.length];
    }

    @Override
    public String getDefaultCurrency() {
        return CURRENCY;
    }

    @Override
    public Coordinate getCoordinate() {
        return new Coordinate(3.14 * id % 90, 2 * 3.14 * id % 180);
    }

    @Override
    public double getRating() {
        return id / 2;
    }

    @Override
    public List<RestaurantLocale> getLocales() {
        return (List) Collections.singletonList(new MockRestaurantLocale(id));
    }

    @Override
    public List<Category> getCategories() {
        return dataSource.getCategories(id);
    }

    @Override
    public List<RestaurantComment> getComments() {
        return dataSource.getRestaurantComments(id);
    }
    
}

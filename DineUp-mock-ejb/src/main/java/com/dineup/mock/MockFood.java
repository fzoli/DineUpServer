package com.dineup.mock;

import com.dineup.ejb.MockDatas;
import com.dineup.dom.Extra;
import com.dineup.dom.Food;
import com.dineup.dom.FoodLocale;
import com.dineup.dom.Price;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockFood implements Food, MockDatas {

    private final int id;
    
    public MockFood(int id) {
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
        List<Extra> list = new ArrayList<>(NUMBER_OF_EXTRAS);
        for (int i = 0; i < NUMBER_OF_FOODS; i++) {
            list.add(new MockExtra(i));
        }
        return Collections.unmodifiableList(list);
    }
    
}

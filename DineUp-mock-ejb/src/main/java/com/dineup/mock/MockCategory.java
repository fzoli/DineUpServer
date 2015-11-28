package com.dineup.mock;

import com.dineup.ejb.MockDatas;
import com.dineup.dom.Category;
import com.dineup.dom.CategoryLocale;
import com.dineup.dom.Food;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MockCategory implements Category, MockDatas {

    private final int id;
    
    public MockCategory(int id) {
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
        List<Food> list = new ArrayList<>(NUMBER_OF_FOODS);
        for (int i = 0; i < NUMBER_OF_FOODS; i++) {
            list.add(new MockFood(i));
        }
        return Collections.unmodifiableList(list);
    }
    
}

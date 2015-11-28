package com.dineup.mock;

import com.dineup.ejb.MockDatas;
import com.dineup.dom.CategoryLocale;

public class MockCategoryLocale implements CategoryLocale, MockDatas {

    private final int id;
    
    public MockCategoryLocale(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return String.format("Category name %s", id);
    }

    @Override
    public String getLanguageCode() {
        return "en";
    }
    
}

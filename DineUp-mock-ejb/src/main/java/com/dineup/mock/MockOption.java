package com.dineup.mock;

import com.dineup.ejb.MockDatas;
import com.dineup.dom.Option;
import com.dineup.dom.OptionLocale;
import com.dineup.dom.Price;
import com.dineup.ejb.RestaurantDataSource;
import java.util.Collections;
import java.util.List;

public class MockOption implements Option, MockDatas {
    private final int id;
    
    public MockOption(RestaurantDataSource dataSource, int id) {
        this.id = id;
    }

    @Override
    public List<Price> getPrices() {
        return (List) Collections.singletonList(new MockPrice());
    }

    @Override
    public List<OptionLocale> getLocales() {
        return (List) Collections.singletonList(new MockOptionLocale(id));
    }
}

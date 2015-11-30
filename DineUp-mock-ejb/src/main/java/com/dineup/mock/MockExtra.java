package com.dineup.mock;

import com.dineup.ejb.MockDatas;
import com.dineup.dom.Extra;
import com.dineup.dom.ExtraLocale;
import com.dineup.dom.Option;
import com.dineup.ejb.RestaurantDataSource;
import java.util.Collections;
import java.util.List;

public class MockExtra implements Extra, MockDatas {

    private final RestaurantDataSource dataSource;
    private final int id;
    
    public MockExtra(RestaurantDataSource dataSource, int id) {
        this.dataSource = dataSource;
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getType() {
        return EXTRA_TYPES[id % EXTRA_TYPES.length];
    }

    @Override
    public List<ExtraLocale> getLocales() {
        return (List) Collections.singletonList(new MockExtraLocale(id));
    }

    @Override
    public List<Option> getOptions() {
        return dataSource.getOptions(id);
    }
    
}

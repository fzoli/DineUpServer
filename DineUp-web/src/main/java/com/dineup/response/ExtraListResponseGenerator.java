package com.dineup.response;

import com.dineup.dom.Extra;
import com.dineup.ejb.RestaurantDataSource;

import java.util.List;

public class ExtraListResponseGenerator implements ResponseGenerator<List<Extra>> {

    private final RestaurantDataSource dataSource;
    private final Query query;

    public interface Query {
        public int getFoodId();
    }

    public ExtraListResponseGenerator(RestaurantDataSource dataSource, Query query) {
        this.dataSource = dataSource;
        this.query = query;
    }

    @Override
    public List<Extra> generateResponse() {
        return dataSource.getExtras(query.getFoodId());
    }

}

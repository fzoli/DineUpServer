package com.dineup.response;

import com.dineup.dom.Category;
import com.dineup.ejb.RestaurantDataSource;
import java.util.List;

public class CategoryListResponseGenerator implements ResponseGenerator<List<Category>> {
    
    private final RestaurantDataSource dataSource;
    private final Query query;

    public interface Query {
        public int getRestaurantId();
    }
    
    public CategoryListResponseGenerator(RestaurantDataSource dataSource, Query query) {
        this.dataSource = dataSource;
        this.query = query;
    }

    @Override
    public List<Category> generateResponse() {
        return dataSource.getCategories(query.getRestaurantId());
    }
    
}

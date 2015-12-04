package com.dineup.ejb.db;

import com.dineup.dom.Category;
import com.dineup.dom.Extra;
import com.dineup.dom.Food;
import com.dineup.dom.Option;
import com.dineup.dom.Restaurant;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RestaurantDataSource {
    public List<Restaurant> getRestaurants();
    public List<Category> getCategories(int restaurantId);
    public List<Food> getFoods(int categoryId);
    public List<Extra> getExtras(int foodId);
    public List<Option> getOptions(int extraId);
}

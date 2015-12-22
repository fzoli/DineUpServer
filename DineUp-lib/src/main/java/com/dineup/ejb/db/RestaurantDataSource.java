package com.dineup.ejb.db;

import com.dineup.dom.Category;
import com.dineup.dom.Extra;
import com.dineup.dom.Food;
import com.dineup.dom.Option;
import com.dineup.dom.Restaurant;
import com.dineup.util.Filter;
import java.util.List;
import javax.ejb.Local;
import com.dineup.dom.Comment;

@Local
public interface RestaurantDataSource {
    public List<Restaurant> getRestaurants(Filter<Restaurant> ... filters);
    public List<Comment> getRestaurantComments(int restaurantId);
    public List<Category> getCategories(int restaurantId);
    public List<Food> getFoods(int categoryId);
    public List<Comment> getFoodComments(int foodId);
    public List<Extra> getExtras(int foodId);
    public List<Option> getOptions(int extraId);
}

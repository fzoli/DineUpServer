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
import com.dineup.ejb.db.data.FoodCommentData;
import com.dineup.ejb.db.data.RestaurantCommentData;

@Local
public interface RestaurantDataSource {
    public Restaurant getRestaurant(int restaurantId);
    public List<Restaurant> getRestaurants(Filter<Restaurant> ... filters);
    public List<Comment> getRestaurantComments(int restaurantId);
    public List<Category> getCategories(int restaurantId);
    public Food getFood(int foodId);
    public List<Food> getFoods(int categoryId);
    public List<Comment> getFoodComments(int foodId);
    public List<Extra> getExtras(int foodId);
    public List<Option> getOptions(int extraId);
    public int addRestaurantComment(RestaurantCommentData commentData);
    public int addFoodComment(FoodCommentData commentData);
}

package com.dineup.api;

import com.dineup.api.dom.Service;
import com.dineup.api.dom.Category;
import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.Extra;
import com.dineup.api.dom.Food;
import com.dineup.api.dom.Option;
import com.dineup.api.dom.Profile;
import com.dineup.api.dom.ProfileToken;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.exception.DetailedException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dineup.api.dom.Comment;
import com.dineup.api.request.FoodCommentRequest;
import com.dineup.api.request.RestaurantCommentRequest;
import com.dineup.api.request.query.RestaurantQuery;

public class Example {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Example.class);
    
    public void hello() {
        try {
            DineUpApi api = createApi();
            Service service = api.getService();
            if (!service.isClientUpToDate()) {
                LOGGER.info("Please upgrade the client.");
                return;
            }
            ProfileToken profileToken = new ProfileToken("abcdef0123456789", Profile.Type.FACEBOOK);
            List<Restaurant> restaurants = api.getRestaurants(RestaurantQuery.newQuery()
                    .coordinate(new Coordinate(5, 6)).maxDistanceInMeters(10000000)
                    .build());
            LOGGER.info("Number of restaurants: " + restaurants.size());
            if (restaurants.isEmpty()) {
                return;
            }
            Restaurant restaurant = restaurants.get(0);
            List<Comment> restaurantComments = api.getRestaurantComments(restaurant, profileToken);
            LOGGER.info("Number of restaurant comments: " + restaurantComments.size());
            List<Category> categories = api.getCategories(restaurant);
            LOGGER.info("Number of categories: " + categories.size());
            if (categories.isEmpty()) {
                return;
            }
            Category category = categories.get(0);
            List<Food> foods = api.getFoods(category);
            LOGGER.info("Number of foods: " + foods.size());
            if (foods.isEmpty()) {
                return;
            }
            Food food = foods.get(0);
            List<Comment> foodComments = api.getFoodComments(food, profileToken);
            LOGGER.info("Number of food comments: " + foodComments.size());
            List<Extra> extras = api.getExtras(food);
            LOGGER.info("Number of extras: " + extras.size());
            if (extras.isEmpty()) {
                return;
            }
            Extra extra = extras.get(0);
            List<Option> options = api.getOptions(extra);
            LOGGER.info("Number of options: " + options.size());
            int createdRestaurantCommentId = api.addRestaurantComment(RestaurantCommentRequest.newBuilder()
                    .restaurant(restaurant)
                    .rating(3)
                    .message("test")
                    .build(), profileToken);
            LOGGER.info("Created restaurant comment ID: " + createdRestaurantCommentId);
            int createdFoodCommentId = api.addFoodComment(FoodCommentRequest.newBuilder()
                    .food(food)
                    .rating(3)
                    .message("test")
                    .build(), profileToken);
            LOGGER.info("Created food comment ID: " + createdFoodCommentId);
        }
        catch (DetailedException ex) {
            LOGGER.error("Error: " + ex, ex);
        }
    }
    
    private DineUpApi createApi() throws DetailedException {
        return HttpClientDineUpApiFactory.newFactory(
            ClientConfig.newBuilder().build(),
            ApiConfig.newBuilder()
                .serverUrl("http://localhost:8080/")
                .webAppRoot("/DineUp")
                .restRoot("/rest")
                .languageCode("hu")
                .build()
        ).createInstance();
    }
    
    public static void main(String[] args) {
        new Example().hello();
    }
    
}

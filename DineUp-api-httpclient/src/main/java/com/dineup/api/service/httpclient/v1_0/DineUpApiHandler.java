package com.dineup.api.service.httpclient.v1_0;

import com.dineup.api.ApiVersion;
import com.dineup.api.DineUpApi;
import com.dineup.api.dom.Service;
import com.dineup.api.ApiConfig;
import com.dineup.api.ClientConfig;
import com.dineup.api.annotation.Nullable;
import com.dineup.api.dom.Category;
import com.dineup.api.dom.Coordinate;
import com.dineup.api.dom.Extra;
import com.dineup.api.dom.Food;
import com.dineup.api.dom.Option;
import com.dineup.api.dom.ProfileToken;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.dom.RestaurantComment;
import com.dineup.api.exception.DetailedException;
import com.dineup.api.service.httpclient.Executable;
import com.dineup.api.service.httpclient.Executor;
import com.dineup.api.service.httpclient.v1_0.element.CategoryElement;
import com.dineup.api.service.httpclient.v1_0.element.ExtraElement;
import com.dineup.api.service.httpclient.v1_0.element.FoodElement;
import com.dineup.api.service.httpclient.v1_0.element.OptionElement;
import com.dineup.api.service.httpclient.v1_0.element.RestaurantCommentElement;
import com.dineup.api.service.httpclient.v1_0.element.RestaurantElement;
import com.dineup.api.service.httpclient.v1_0.element.ServiceElement;
import com.dineup.service.rest.ElementConfigKeys;
import com.dineup.service.rest.RequestPath;
import com.dineup.service.rest.RestaurantKeys;
import com.dineup.util.Lists;
import com.dineup.util.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DineUpApiHandler implements DineUpApi {

    private final Executor executor;
    private final ApiConfig targetConfig;
    private final String apiVersion;
    
    public DineUpApiHandler(ClientConfig clientConfig, ApiConfig targetConfig, ApiVersion apiVersion) {
        this.executor = new Executor(clientConfig, targetConfig);
        this.targetConfig = targetConfig;
        this.apiVersion = apiVersion.getVersion();
    }
    
    @Override
    public Service getService() throws DetailedException {
        return executor.execute(new GetService());
    }
    
    @Override
    public List<Restaurant> getRestaurants(final Coordinate coordinate) throws DetailedException {
        return executor.execute(new GetRestaurants(coordinate));
    }

    @Override
    public List<RestaurantComment> getRestaurantComments(Restaurant restaurant, @Nullable ProfileToken profileToken) throws DetailedException {
        return executor.execute(new GetRestaurantComments(restaurant.getId(), profileToken));
    }
    
    @Override
    public List<Category> getCategories(Restaurant restaurant) throws DetailedException {
        return executor.execute(new GetCategories(restaurant.getId()));
    }
    
    @Override
    public List<Food> getFoods(Category category) throws DetailedException {
        return executor.execute(new GetFoods(category.getId()));
    }
    
    @Override
    public List<Extra> getExtras(Food food) throws DetailedException {
        return executor.execute(new GetExtras(food.getId()));
    }
    
    @Override
    public List<Option> getOptions(Extra extra) throws DetailedException {
        return executor.execute(new GetOptions(extra.getId()));
    }
    
    private class GetService extends Executable<Service> {

        public GetService() {
        }

        @Override
        public void appendPath(StringBuilder path) { // TODO: replace string builder
            path.append("/"+apiVersion).append(RequestPath.PATH_SERVICE);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
        }
        
        @Override
        public Service parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<ServiceElement>() {}.getType();
            ServiceElement entity = gson.fromJson(jsonReader, entityType);
            return entity;
        }
        
    }
    
    private class GetRestaurants extends Executable<List<Restaurant>> {

        private final Coordinate coordinate;
        
        public GetRestaurants(Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            if (coordinate != null) {
                parameters.put(ElementConfigKeys.LATITUDE, coordinate.getLatitude());
                parameters.put(ElementConfigKeys.LONGITUDE, coordinate.getLongitude());
            }
        }

        @Override
        public void appendPath(StringBuilder path) {
            path.append("/"+apiVersion).append(RequestPath.PATH_RESTAURANTS);
        }

        @Override
        public List<Restaurant> parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<List<RestaurantElement>>() {}.getType();
            List<RestaurantElement> entity = gson.fromJson(jsonReader, entityType);
            List<Restaurant> list = Lists.convert(entity);
            return Collections.unmodifiableList(list);
        }
        
    }
    
    private class GetRestaurantComments extends Executable<List<RestaurantComment>> {

        private final int restaurantId;
        private final ProfileToken profileToken;
        
        public GetRestaurantComments(int restaurantId, ProfileToken profileToken) {
            this.restaurantId = restaurantId;
            this.profileToken = profileToken;
        }

        @Override
        public void appendPath(StringBuilder path) {
            path.append("/"+apiVersion).append(RequestPath.PATH_RESTAURANT_COMMENTS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.RESTAURANT_ID, restaurantId);
            if (profileToken != null) {
                switch (profileToken.getType()) {
                    case FACEBOOK:
                        parameters.put(ElementConfigKeys.FACEBOOK_ACCESS_TOKEN, profileToken.getAccessToken());
                        break;
                    case GOOGLE_PLUS:
                        parameters.put(ElementConfigKeys.GOOGLE_ACCESS_TOKEN, profileToken.getAccessToken());
                        break;
                }
            }
        }

        @Override
        public List<RestaurantComment> parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<List<RestaurantCommentElement>>() {}.getType();
            List<RestaurantCommentElement> entity = gson.fromJson(jsonReader, entityType);
            format(entity);
            List<RestaurantComment> list = Lists.convert(entity);
            return Collections.unmodifiableList(list);
        }
        
        private void format(List<RestaurantCommentElement> entity) {
            for (RestaurantCommentElement element : entity) {
                if (element.profile != null) {
                    String photoUrl = element.profile.photoUrl;
                    if (photoUrl != null && photoUrl.startsWith("/")) {
                        element.profile.photoUrl = Strings.concat("/", targetConfig.getServerUrl(), photoUrl);
                    }
                }
            }
        }
        
    }
    
    private class GetCategories extends Executable<List<Category>> {

        private final int restaurantId;
        
        public GetCategories(int restaurantId) {
            this.restaurantId = restaurantId;
        }

        @Override
        public void appendPath(StringBuilder path) {
            path.append("/"+apiVersion).append(RequestPath.PATH_CATEGORIES);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.RESTAURANT_ID, restaurantId);
        }

        @Override
        public List<Category> parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<List<CategoryElement>>() {}.getType();
            List<CategoryElement> entity = gson.fromJson(jsonReader, entityType);
            List<Category> list = Lists.convert(entity);
            return Collections.unmodifiableList(list);
        }
        
    }
    
    private class GetFoods extends Executable<List<Food>> {

        private final int categoryId;
        
        public GetFoods(int categoryId) {
            this.categoryId = categoryId;
        }

        @Override
        public void appendPath(StringBuilder path) {
            path.append("/"+apiVersion).append(RequestPath.PATH_FOODS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.CATEGORY_ID, categoryId);
        }

        @Override
        public List<Food> parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<List<FoodElement>>() {}.getType();
            List<FoodElement> entity = gson.fromJson(jsonReader, entityType);
            List<Food> list = Lists.convert(entity);
            return Collections.unmodifiableList(list);
        }
        
    }
    
    private class GetExtras extends Executable<List<Extra>> {

        private final int foodId;
        
        public GetExtras(int foodId) {
            this.foodId = foodId;
        }

        @Override
        public void appendPath(StringBuilder path) {
            path.append("/"+apiVersion).append(RequestPath.PATH_EXTRAS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.FOOD_ID, foodId);
        }

        @Override
        public List<Extra> parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<List<ExtraElement>>() {}.getType();
            List<ExtraElement> entity = gson.fromJson(jsonReader, entityType);
            List<Extra> list = Lists.convert(entity);
            return Collections.unmodifiableList(list);
        }
        
    }
    
    private class GetOptions extends Executable<List<Option>> {

        private final int extraId;
        
        public GetOptions(int extraId) {
            this.extraId = extraId;
        }

        @Override
        public void appendPath(StringBuilder path) {
            path.append("/"+apiVersion).append(RequestPath.PATH_OPTIONS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.EXTRA_ID, extraId);
        }

        @Override
        public List<Option> parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<List<OptionElement>>() {}.getType();
            List<OptionElement> entity = gson.fromJson(jsonReader, entityType);
            List<Option> list = Lists.convert(entity);
            return Collections.unmodifiableList(list);
        }
        
    }
    
}

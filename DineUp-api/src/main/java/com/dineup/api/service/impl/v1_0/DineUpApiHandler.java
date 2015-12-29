package com.dineup.api.service.impl.v1_0;

import com.dineup.api.ApiVersion;
import com.dineup.api.DineUpApi;
import com.dineup.api.dom.Service;
import com.dineup.api.ApiConfig;
import com.dineup.api.DineUpCache;
import com.dineup.api.DineUpCacheManager;
import com.dineup.api.annotation.Nullable;
import com.dineup.api.dom.Category;
import com.dineup.api.dom.Extra;
import com.dineup.api.dom.Food;
import com.dineup.api.dom.Option;
import com.dineup.api.dom.ProfileToken;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.exception.DetailedException;
import com.dineup.api.service.impl.Executable;
import com.dineup.api.service.impl.Executor;
import com.dineup.api.service.impl.v1_0.element.CategoryElement;
import com.dineup.api.service.impl.v1_0.element.ExtraElement;
import com.dineup.api.service.impl.v1_0.element.FoodElement;
import com.dineup.api.service.impl.v1_0.element.OptionElement;
import com.dineup.api.service.impl.v1_0.element.CommentElement;
import com.dineup.api.service.impl.v1_0.element.RestaurantElement;
import com.dineup.api.service.impl.v1_0.element.ServiceElement;
import com.dineup.service.rest.RequestPath;
import com.dineup.service.rest.RestaurantKeys;
import com.dineup.util.Lists;
import com.dineup.util.string.StringConcatenator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.dineup.api.dom.Comment;
import com.dineup.api.request.FoodCommentRequest;
import com.dineup.api.request.RestaurantCommentRequest;
import com.dineup.api.request.query.RestaurantQuery;
import com.dineup.api.service.dom.ServiceCategory;
import com.dineup.api.service.dom.ServiceExtra;
import com.dineup.api.service.dom.ServiceFood;
import com.dineup.api.service.dom.ServiceRestaurant;
import com.dineup.util.Strings;
import com.dineup.common.dom.Coordinates;
import java.util.Date;
import java.util.ListIterator;
import com.dineup.api.service.dom.ServiceModificationDates;
import com.dineup.api.service.impl.CacheableExecutable;

public class DineUpApiHandler implements DineUpApi {

    private final Executor executor;
    private final ApiConfig apiConfig;
    private final String apiVersion;
    
    public DineUpApiHandler(ApiConfig apiConfig, ApiVersion apiVersion) {
        this.executor = new Executor(apiConfig);
        this.apiConfig = apiConfig;
        this.apiVersion = apiVersion.getVersion();
    }
    
    private ServiceModificationDates getModificationDates() throws DetailedException {
        // TODO
        return new ServiceModificationDates() {

            @Override
            public Date restaurants() {
                return null; 
            }

            @Override
            public Date restaurant(int restaurantId) {
                return null;
            }
        };
    }
    
    @Override
    public Service getService() throws DetailedException {
        return execute(new GetService());
    }
    
    @Override
    public List<Restaurant> getRestaurants(RestaurantQuery query) throws DetailedException {
        List<Restaurant> list = execute(new GetRestaurants());
        if (query == null || query.getCoordinate() == null) {
            return list;
        }
        ListIterator<Restaurant> it = list.listIterator();
        while (it.hasNext()) {
            Restaurant r = it.next();
            if (r.getCoordinate() != null) {
                Double distance = Coordinates.getDistanceInMeter(r.getCoordinate(), query.getCoordinate());
                if (r instanceof ServiceRestaurant) {
                    ((ServiceRestaurant) r).setDistance(distance);
                }
                if (query.getMaxDistanceInMeters() != null && r.getDistance() > query.getMaxDistanceInMeters()) {
                    it.remove();
                }
            }
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public List<Comment> getRestaurantComments(Restaurant restaurant, @Nullable ProfileToken profileToken) throws DetailedException {
        List<Comment> list = execute(new GetRestaurantComments(restaurant, profileToken));
        return Collections.unmodifiableList(list);
    }
    
    @Override
    public List<Category> getCategories(Restaurant restaurant) throws DetailedException {
        List<Category> list = execute(new GetCategories(restaurant));
        return Collections.unmodifiableList(list);
    }
    
    @Override
    public List<Food> getFoods(Category category) throws DetailedException {
        List<Food> list = execute(new GetFoods(category));
        return Collections.unmodifiableList(list);
    }
    
    @Override
    public List<Comment> getFoodComments(Food food, ProfileToken profileToken) throws DetailedException {
        List<Comment> list = execute(new GetFoodComments(food, profileToken));
        return Collections.unmodifiableList(list);
    }
    
    @Override
    public List<Extra> getExtras(Food food) throws DetailedException {
        List<Extra> list = execute(new GetExtras(food));
        return Collections.unmodifiableList(list);
    }
    
    @Override
    public List<Option> getOptions(Extra extra) throws DetailedException {
        List<Option> list = execute(new GetOptions(extra));
        return Collections.unmodifiableList(list);
    }

    @Override
    public int addRestaurantComment(RestaurantCommentRequest restaurantCommentRequest, ProfileToken profileToken) throws DetailedException {
        return execute(new AddRestaurantComment(restaurantCommentRequest, profileToken));
    }

    @Override
    public int addFoodComment(FoodCommentRequest foodCommentRequest, ProfileToken profileToken) throws DetailedException {
        return execute(new AddFoodComment(foodCommentRequest, profileToken));
    }
    
    private <T> T execute(Executable<T> executable) throws DetailedException {
        return executor.execute(executable);
    }
    
    private <T> T execute(CacheableExecutable<T> executable) throws DetailedException {
        return execute(executable, executable);
    }
    
    private <T> T execute(Executable<T> executable, CacheableExecutable<T> cacheable) throws DetailedException {
        Date remoteLastModified = cacheable.getModificationDate(getModificationDates());
        DineUpCache cache = cacheable.getCache(apiConfig.getCacheManager());
        DineUpCache.CacheResult<T> result = null;
        try {
            result = cache.get();
        }
        catch (Exception ex) {
            // Failed to load the cache. Not important.
        }
        if (result != null) {
            boolean upToDate = remoteLastModified != null && result.lastModified() != null && result.lastModified().getTime() >= remoteLastModified.getTime();
            boolean compatibleApi = result.apiVersion() != null && result.apiVersion().equals(apiVersion);
            if (upToDate && compatibleApi) {
                return result.data();
            }
        }
        T data = execute(executable);
        try {
            cache.set(data, apiVersion, apiConfig.getLanguageCode());
        }
        catch (Exception ex) {
            // Failed to cache the loaded data. Not important.
        }
        return data;
    }
    
    private class GetService implements Executable<Service> {

        public GetService() {
        }

        @Override
        public void appendPath(StringConcatenator path) {
            path.addItems(apiVersion, RequestPath.PATH_SERVICE);
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
    
    private class GetRestaurants implements CacheableExecutable<List<Restaurant>> {

        @Override
        public void putParameters(Map<String, Object> parameters) {
        }

        @Override
        public void appendPath(StringConcatenator path) {
            path.addItems(apiVersion, RequestPath.PATH_RESTAURANTS);
        }

        @Override
        public List<Restaurant> parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<List<RestaurantElement>>() {}.getType();
            List<RestaurantElement> entity = gson.fromJson(jsonReader, entityType);
            return Lists.convert(entity);
        }

        @Override
        public DineUpCache<List<Restaurant>> getCache(DineUpCacheManager cacheManager) {
            return cacheManager.getRestaurants();
        }

        @Override
        public Date getModificationDate(ServiceModificationDates dates) {
            return dates.restaurants();
        }
        
    }
    
    private class GetCategories implements CacheableExecutable<List<Category>> {

        private final Restaurant restaurant;
        
        public GetCategories(Restaurant restaurant) {
            this.restaurant = restaurant;
        }

        @Override
        public void appendPath(StringConcatenator path) {
            path.addItems(apiVersion, RequestPath.PATH_CATEGORIES);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.RESTAURANT_ID, restaurant.getId());
        }

        @Override
        public List<Category> parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<List<CategoryElement>>() {}.getType();
            List<CategoryElement> entity = gson.fromJson(jsonReader, entityType);
            complete(entity);
            List<Category> list = Lists.convert(entity);
            return list;
        }

        private void complete(List<CategoryElement> entity) {
            for (CategoryElement element : entity) {
                element.restaurantId = restaurant.getId();
            }
        }
        
        @Override
        public DineUpCache<List<Category>> getCache(DineUpCacheManager cacheManager) {
            return cacheManager.getCategories(restaurant);
        }

        @Override
        public Date getModificationDate(ServiceModificationDates dates) {
            return dates.restaurant(restaurant.getId());
        }
        
    }
    
    private class GetFoods implements CacheableExecutable<List<Food>> {

        private final Category category;
        
        public GetFoods(Category category) {
            this.category = category;
        }

        @Override
        public void appendPath(StringConcatenator path) {
            path.addItems(apiVersion, RequestPath.PATH_FOODS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.CATEGORY_ID, category.getId());
        }

        @Override
        public List<Food> parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<List<FoodElement>>() {}.getType();
            List<FoodElement> entity = gson.fromJson(jsonReader, entityType);
            complete(entity);
            List<Food> list = Lists.convert(entity);
            return list;
        }

        private void complete(List<FoodElement> entity) {
            for (FoodElement element : entity) {
                element.restaurantId = ((ServiceCategory) category).getRestaurantId();
            }
        }
        
        @Override
        public DineUpCache<List<Food>> getCache(DineUpCacheManager cacheManager) {
            return cacheManager.getFoods(category);
        }

        @Override
        public Date getModificationDate(ServiceModificationDates dates) {
            return dates.restaurant(((ServiceCategory) category).getRestaurantId());
        }
        
    }
    
    private class GetExtras implements CacheableExecutable<List<Extra>> {

        private final Food food;
        
        public GetExtras(Food food) {
            this.food = food;
        }

        @Override
        public void appendPath(StringConcatenator path) {
            path.addItems(apiVersion, RequestPath.PATH_EXTRAS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.FOOD_ID, food.getId());
        }

        @Override
        public List<Extra> parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<List<ExtraElement>>() {}.getType();
            List<ExtraElement> entity = gson.fromJson(jsonReader, entityType);
            complete(entity);
            List<Extra> list = Lists.convert(entity);
            return list;
        }

        private void complete(List<ExtraElement> entity) {
            for (ExtraElement element : entity) {
                element.restaurantId = ((ServiceFood)food).getRestaurantId();
            }
        }
        
        @Override
        public DineUpCache<List<Extra>> getCache(DineUpCacheManager cacheManager) {
            return cacheManager.getExtras(food);
        }

        @Override
        public Date getModificationDate(ServiceModificationDates dates) {
            return dates.restaurant(((ServiceFood)food).getRestaurantId());
        }
        
    }
    
    private class GetOptions implements CacheableExecutable<List<Option>> {

        private final Extra extra;
        
        public GetOptions(Extra extra) {
            this.extra = extra;
        }

        @Override
        public void appendPath(StringConcatenator path) {
            path.addItems(apiVersion, RequestPath.PATH_OPTIONS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.EXTRA_ID, extra.getId());
        }

        @Override
        public List<Option> parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<List<OptionElement>>() {}.getType();
            List<OptionElement> entity = gson.fromJson(jsonReader, entityType);
            complete(entity);
            List<Option> list = Lists.convert(entity);
            return list;
        }

        private void complete(List<OptionElement> entity) {
            for (OptionElement element : entity) {
                element.restaurantId = ((ServiceExtra)extra).getRestaurantId();
            }
        }

        @Override
        public DineUpCache<List<Option>> getCache(DineUpCacheManager cacheManager) {
            return cacheManager.getOptions(extra);
        }

        @Override
        public Date getModificationDate(ServiceModificationDates dates) {
            return dates.restaurant(((ServiceExtra)extra).getRestaurantId());
        }
        
    }
    
    private class GetRestaurantComments implements Executable<List<Comment>> {

        private final Restaurant restaurant;
        private final ProfileToken profileToken;
        
        public GetRestaurantComments(Restaurant restaurant, ProfileToken profileToken) {
            this.restaurant = restaurant;
            this.profileToken = profileToken;
        }

        @Override
        public void appendPath(StringConcatenator path) {
            path.addItems(apiVersion, RequestPath.PATH_RESTAURANT_COMMENTS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.RESTAURANT_ID, restaurant.getId());
            Utils.putProfileParameters(parameters, profileToken);
        }

        @Override
        public List<Comment> parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<List<CommentElement>>() {}.getType();
            List<CommentElement> entity = gson.fromJson(jsonReader, entityType);
            complete(entity);
            List<Comment> list = Lists.convert(entity);
            return list;
        }
        
        private void complete(List<CommentElement> entity) {
            for (CommentElement element : entity) {
                Utils.completeProfileElement(element.profile, apiConfig);
            }
        }
        
    }
    
    private class GetFoodComments implements Executable<List<Comment>> {

        private final Food food;
        private final ProfileToken profileToken;
        
        public GetFoodComments(Food food, ProfileToken profileToken) {
            this.food = food;
            this.profileToken = profileToken;
        }

        @Override
        public void appendPath(StringConcatenator path) {
            path.addItems(apiVersion, RequestPath.PATH_FOOD_COMMENTS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.FOOD_ID, food.getId());
            Utils.putProfileParameters(parameters, profileToken);
        }

        @Override
        public List<Comment> parseResponse(Gson gson, JsonReader jsonReader) {
            Type entityType = new TypeToken<List<CommentElement>>() {}.getType();
            List<CommentElement> entity = gson.fromJson(jsonReader, entityType);
            complete(entity);
            List<Comment> list = Lists.convert(entity);
            return list;
        }
        
        private void complete(List<CommentElement> entity) {
            for (CommentElement element : entity) {
                Utils.completeProfileElement(element.profile, apiConfig);
            }
        }
        
    }
    
    private class AddRestaurantComment implements Executable<Integer> {

        private final RestaurantCommentRequest restaurantCommentRequest;
        private final ProfileToken profileToken;
        
        public AddRestaurantComment(RestaurantCommentRequest restaurantCommentRequest, ProfileToken profileToken) {
            this.restaurantCommentRequest = restaurantCommentRequest;
            this.profileToken = profileToken;
        }

        @Override
        public void appendPath(StringConcatenator path) {
            path.addItems(apiVersion, RequestPath.PATH_ADD_RESTAURANT_COMMENT);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.RESTAURANT_ID, restaurantCommentRequest.getRestaurant().getId());
            parameters.put(RestaurantKeys.RATING, restaurantCommentRequest.getRating());
            if (!Strings.isEmptyText(restaurantCommentRequest.getMessage())) {
                parameters.put(RestaurantKeys.MESSAGE, restaurantCommentRequest.getMessage());
            }
            Utils.putProfileParameters(parameters, profileToken);
        }

        @Override
        public Integer parseResponse(Gson gson, JsonReader jsonReader) {
            Integer id = gson.fromJson(jsonReader, Integer.class);
            return id;
        }
        
    }
    
    private class AddFoodComment implements Executable<Integer> {

        private final FoodCommentRequest foodCommentRequest;
        private final ProfileToken profileToken;
        
        public AddFoodComment(FoodCommentRequest foodCommentRequest, ProfileToken profileToken) {
            this.foodCommentRequest = foodCommentRequest;
            this.profileToken = profileToken;
        }

        @Override
        public void appendPath(StringConcatenator path) {
            path.addItems(apiVersion, RequestPath.PATH_ADD_FOOD_COMMENT);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.FOOD_ID, foodCommentRequest.getFood().getId());
            parameters.put(RestaurantKeys.RATING, foodCommentRequest.getRating());
            if (!Strings.isEmptyText(foodCommentRequest.getMessage())) {
                parameters.put(RestaurantKeys.MESSAGE, foodCommentRequest.getMessage());
            }
            Utils.putProfileParameters(parameters, profileToken);
        }

        @Override
        public Integer parseResponse(Gson gson, JsonReader jsonReader) {
            Integer id = gson.fromJson(jsonReader, Integer.class);
            return id;
        }
        
    }
    
}

package com.dineup.api.service.jaxrs.v1_0;

import com.dineup.api.ApiVersion;
import com.dineup.api.DineUpApi;
import com.dineup.api.dom.Service;
import com.dineup.api.ApiConfig;
import com.dineup.api.annotation.Nullable;
import com.dineup.api.dom.Category;
import com.dineup.api.dom.Extra;
import com.dineup.api.dom.Food;
import com.dineup.api.dom.Option;
import com.dineup.api.dom.ProfileToken;
import com.dineup.api.dom.Restaurant;
import com.dineup.api.exception.DetailedException;
import com.dineup.api.service.jaxrs.Executable;
import com.dineup.api.service.jaxrs.Executor;
import com.dineup.api.service.jaxrs.v1_0.element.CategoryElement;
import com.dineup.api.service.jaxrs.v1_0.element.ExtraElement;
import com.dineup.api.service.jaxrs.v1_0.element.FoodElement;
import com.dineup.api.service.jaxrs.v1_0.element.OptionElement;
import com.dineup.api.service.jaxrs.v1_0.element.RestaurantCommentElement;
import com.dineup.api.service.jaxrs.v1_0.element.RestaurantElement;
import com.dineup.api.service.jaxrs.v1_0.element.ServiceElement;
import com.dineup.service.rest.ElementConfigKeys;
import com.dineup.service.rest.RequestPath;
import com.dineup.service.rest.RestaurantKeys;
import com.dineup.util.Lists;
import com.dineup.util.Strings;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import com.dineup.api.dom.Comment;
import com.dineup.api.request.FoodCommentRequest;
import com.dineup.api.request.RestaurantCommentRequest;
import com.dineup.api.request.query.RestaurantQuery;

public class DineUpApiHandler implements DineUpApi {

    private final Executor executor;
    private final ApiConfig targetConfig;
    private final String apiVersion;
    
    public DineUpApiHandler(Client client, ApiConfig targetConfig, ApiVersion apiVersion) {
        this.executor = new Executor(client, targetConfig);
        this.targetConfig = targetConfig;
        this.apiVersion = apiVersion.getVersion();
    }
    
    @Override
    public Service getService() throws DetailedException {
        return executor.execute(new GetService());
    }
    
    @Override
    public List<Restaurant> getRestaurants(RestaurantQuery query) throws DetailedException {
        return executor.execute(new GetRestaurants(query));
    }

    @Override
    public List<Comment> getRestaurantComments(Restaurant restaurant, @Nullable ProfileToken profileToken) throws DetailedException {
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
    public List<Comment> getFoodComments(Food food, ProfileToken profileToken) throws DetailedException {
        throw new UnsupportedOperationException("Not supported yet."); // TODO
    }
    
    @Override
    public List<Extra> getExtras(Food food) throws DetailedException {
        return executor.execute(new GetExtras(food.getId()));
    }
    
    @Override
    public List<Option> getOptions(Extra extra) throws DetailedException {
        return executor.execute(new GetOptions(extra.getId()));
    }

    @Override
    public int addRestaurantComment(RestaurantCommentRequest restaurantCommentRequest, ProfileToken profileToken) throws DetailedException {
        throw new UnsupportedOperationException("Not supported yet."); // TODO
    }

    @Override
    public int addFoodComment(FoodCommentRequest foodCommentRequest, ProfileToken profileToken) throws DetailedException {
        throw new UnsupportedOperationException("Not supported yet."); // TODO
    }
    
    private class GetService extends Executable<Service> {

        public GetService() {
        }

        @Override
        public WebTarget appendPath(WebTarget target) {
            return target
                    .path(apiVersion)
                    .path(RequestPath.PATH_SERVICE);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
        }

        @Override
        public Service parseResponse(Response response) {
            GenericType<ServiceElement> type = new GenericType<ServiceElement>(){};
            ServiceElement entity = response.readEntity(type);
            return entity;
        }
        
    }
    
    private class GetRestaurants extends Executable<List<Restaurant>> {

        private final RestaurantQuery query;
        
        public GetRestaurants(RestaurantQuery query) {
            this.query = query;
        }

        @Override
        public WebTarget appendPath(WebTarget target) {
            return target
                    .path(apiVersion)
                    .path(RequestPath.PATH_RESTAURANTS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            if (query == null) {
                return;
            }
            if (query.getCoordinate() != null) {
                parameters.put(ElementConfigKeys.LATITUDE, query.getCoordinate().getLatitude());
                parameters.put(ElementConfigKeys.LONGITUDE, query.getCoordinate().getLongitude());
            }
            if (query.getMaxDistanceInMeters() != null) {
                parameters.put(ElementConfigKeys.RADIUS, query.getMaxDistanceInMeters());
            }
        }

        @Override
        public List<Restaurant> parseResponse(Response response) {
            GenericType<List<RestaurantElement>> type = new GenericType<List<RestaurantElement>>(){};
            List<RestaurantElement> entity = response.readEntity(type);
            List<Restaurant> list = Lists.convert(entity);
            return Collections.unmodifiableList(list);
        }
        
    }
    
    private class GetRestaurantComments extends Executable<List<Comment>> {

        private final int restaurantId;
        private final ProfileToken profileToken;
        
        public GetRestaurantComments(int restaurantId, ProfileToken profileToken) {
            this.restaurantId = restaurantId;
            this.profileToken = profileToken;
        }

        @Override
        public WebTarget appendPath(WebTarget target) {
            return target
                    .path(apiVersion)
                    .path(RequestPath.PATH_RESTAURANT_COMMENTS);
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
        public List<Comment> parseResponse(Response response) {
            GenericType<List<RestaurantCommentElement>> type = new GenericType<List<RestaurantCommentElement>>(){};
            List<RestaurantCommentElement> entity = response.readEntity(type);
            format(entity);
            List<Comment> list = Lists.convert(entity);
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
        public WebTarget appendPath(WebTarget target) {
            return target
                    .path(apiVersion)
                    .path(RequestPath.PATH_CATEGORIES);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.RESTAURANT_ID, restaurantId);
        }

        @Override
        public List<Category> parseResponse(Response response) {
            GenericType<List<CategoryElement>> type = new GenericType<List<CategoryElement>>(){};
            List<CategoryElement> entity = response.readEntity(type);
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
        public WebTarget appendPath(WebTarget target) {
            return target
                    .path(apiVersion)
                    .path(RequestPath.PATH_FOODS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.CATEGORY_ID, categoryId);
        }

        @Override
        public List<Food> parseResponse(Response response) {
            GenericType<List<FoodElement>> type = new GenericType<List<FoodElement>>(){};
            List<FoodElement> entity = response.readEntity(type);
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
        public WebTarget appendPath(WebTarget target) {
            return target
                    .path(apiVersion)
                    .path(RequestPath.PATH_EXTRAS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.FOOD_ID, foodId);
        }

        @Override
        public List<Extra> parseResponse(Response response) {
            GenericType<List<ExtraElement>> type = new GenericType<List<ExtraElement>>(){};
            List<ExtraElement> entity = response.readEntity(type);
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
        public WebTarget appendPath(WebTarget target) {
            return target
                    .path(apiVersion)
                    .path(RequestPath.PATH_OPTIONS);
        }

        @Override
        public void putParameters(Map<String, Object> parameters) {
            parameters.put(RestaurantKeys.EXTRA_ID, extraId);
        }

        @Override
        public List<Option> parseResponse(Response response) {
            GenericType<List<OptionElement>> type = new GenericType<List<OptionElement>>(){};
            List<OptionElement> entity = response.readEntity(type);
            List<Option> list = Lists.convert(entity);
            return Collections.unmodifiableList(list);
        }
        
    }
    
}

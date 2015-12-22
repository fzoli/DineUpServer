package com.dineup.ejb.rest;

import com.dineup.dom.Area;
import com.dineup.dom.Category;
import com.dineup.service.ElementContext;
import com.dineup.dom.Extra;
import com.dineup.dom.Food;
import com.dineup.dom.Option;
import com.dineup.service.rest.HeaderKeys;
import com.dineup.dom.Restaurant;
import com.dineup.dom.filter.RestaurantDistanceFilter;
import com.dineup.ejb.db.RestaurantDataSource;
import com.dineup.ejb.error.ErrorResponseFactory;
import com.dineup.ejb.profile.ProfileManagerFactory;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.CategoryElement;
import com.dineup.service.element.ExtraElement;
import com.dineup.service.element.FoodElement;
import com.dineup.service.element.OptionElement;
import com.dineup.service.element.CommentElement;
import com.dineup.service.element.RestaurantElement;
import javax.ws.rs.core.Response;
import com.dineup.service.element.converter.CategoryElementConverter;
import com.dineup.service.element.converter.ExtraElementConverter;
import com.dineup.service.element.converter.FoodElementConverter;
import com.dineup.service.element.converter.OptionElementConverter;
import com.dineup.service.element.converter.CommentElementConverter;
import com.dineup.service.element.converter.RestaurantElementConverter;
import com.dineup.service.error.exception.UnspecifiedIdentifierException;
import com.dineup.util.Converters;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.core.GenericEntity;
import com.dineup.dom.Comment;

@Singleton
public class RestaurantRestResourceBean implements RestaurantRestResource, HeaderKeys {
    
    @EJB
    private RestaurantDataSource dataSource;

    @EJB
    private ProfileManagerFactory profileManagerFactory;
    
    @EJB
    private ErrorResponseFactory errorResponseFactory;
    
    private ElementContext createElementContext() {
        return ElementContext.newBuilder()
                .profileManagerFactory(profileManagerFactory)
                .build();
    }
    
    @Override
    public Response getRestaurants(ElementConfig elementConfig) {
        try {
            elementConfig.validate();
            Area area = elementConfig.createArea();
            List<Restaurant> objects = dataSource.getRestaurants(
                    new RestaurantDistanceFilter(area)
            );
            List<RestaurantElement> elements = Converters.convertList(objects, new RestaurantElementConverter(createElementContext(), elementConfig));
            GenericEntity<List<RestaurantElement>> entity = new GenericEntity<List<RestaurantElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return createErrorResponse(elementConfig, ex);
        }
    }

    @Override
    public Response getRestaurantComments(ElementConfig elementConfig, Integer restaurantId) {
        try {
            if (restaurantId == null) {
                throw new UnspecifiedIdentifierException("Unspecified restaurantId");
            }
            elementConfig.validate();
            List<Comment> objects = dataSource.getRestaurantComments(restaurantId);
            List<CommentElement> elements = Converters.convertList(objects, new CommentElementConverter(createElementContext(), elementConfig));
            GenericEntity<List<CommentElement>> entity = new GenericEntity<List<CommentElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return createErrorResponse(elementConfig, ex);
        }
    }
    
    @Override
    public Response getCategories(ElementConfig elementConfig, Integer restaurantId) {
        try {
            if (restaurantId == null) {
                throw new UnspecifiedIdentifierException("Unspecified restaurantId");
            }
            elementConfig.validate();
            List<Category> objects = dataSource.getCategories(restaurantId);
            List<CategoryElement> elements = Converters.convertList(objects, new CategoryElementConverter(createElementContext(), elementConfig));
            GenericEntity<List<CategoryElement>> entity = new GenericEntity<List<CategoryElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return createErrorResponse(elementConfig, ex);
        }
    }
    
    @Override
    public Response getFoods(ElementConfig elementConfig, Integer categoryId) {
        try {
            if (categoryId == null) {
                throw new UnspecifiedIdentifierException("Unspecified categoryId");
            }
            elementConfig.validate();
            List<Food> objects = dataSource.getFoods(categoryId);
            List<FoodElement> elements = Converters.convertList(objects, new FoodElementConverter(createElementContext(), elementConfig));
            GenericEntity<List<FoodElement>> entity = new GenericEntity<List<FoodElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return createErrorResponse(elementConfig, ex);
        }
    }
    
    @Override
    public Response getFoodComments(ElementConfig elementConfig, Integer foodId) {
        try {
            if (foodId == null) {
                throw new UnspecifiedIdentifierException("Unspecified foodId");
            }
            elementConfig.validate();
            List<Comment> objects = dataSource.getFoodComments(foodId);
            List<CommentElement> elements = Converters.convertList(objects, new CommentElementConverter(createElementContext(), elementConfig));
            GenericEntity<List<CommentElement>> entity = new GenericEntity<List<CommentElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return createErrorResponse(elementConfig, ex);
        }
    }
    
    @Override
    public Response getExtras(ElementConfig elementConfig, Integer foodId) {
        try {
            if (foodId == null) {
                throw new UnspecifiedIdentifierException("Unspecified foodId");
            }
            elementConfig.validate();
            List<Extra> objects = dataSource.getExtras(foodId);
            List<ExtraElement> elements = Converters.convertList(objects, new ExtraElementConverter(createElementContext(), elementConfig));
            GenericEntity<List<ExtraElement>> entity = new GenericEntity<List<ExtraElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return createErrorResponse(elementConfig, ex);
        }
    }

    @Override
    public Response getOptions(ElementConfig elementConfig, Integer extraId) {
        try {
            if (extraId == null) {
                throw new UnspecifiedIdentifierException("Unspecified extraId");
            }
            elementConfig.validate();
            List<Option> objects = dataSource.getOptions(extraId);
            List<OptionElement> elements = Converters.convertList(objects, new OptionElementConverter(createElementContext(), elementConfig));
            GenericEntity<List<OptionElement>> entity = new GenericEntity<List<OptionElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return createErrorResponse(elementConfig, ex);
        }
    }
    
    private Response createErrorResponse(ElementConfig elementConfig, Exception ex) {
        return errorResponseFactory.createResponse(ex, elementConfig.getLanguageCode());
    }

}

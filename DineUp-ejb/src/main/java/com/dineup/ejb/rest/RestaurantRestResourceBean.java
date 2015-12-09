package com.dineup.ejb.rest;

import com.dineup.dom.Category;
import com.dineup.rest.ElementContext;
import com.dineup.dom.Extra;
import com.dineup.dom.Food;
import com.dineup.dom.Option;
import com.dineup.rest.HeaderKeys;
import com.dineup.dom.Restaurant;
import com.dineup.dom.RestaurantComment;
import com.dineup.ejb.db.RestaurantDataSource;
import com.dineup.ejb.profile.ProfileManagerFactory;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.CategoryElement;
import com.dineup.rest.element.ExtraElement;
import com.dineup.rest.element.FoodElement;
import com.dineup.rest.element.OptionElement;
import com.dineup.rest.element.RestaurantCommentElement;
import com.dineup.rest.element.RestaurantElement;
import javax.ws.rs.core.Response;
import com.dineup.rest.element.converter.CategoryElementConverter;
import com.dineup.rest.element.converter.ExtraElementConverter;
import com.dineup.rest.element.converter.FoodElementConverter;
import com.dineup.rest.element.converter.OptionElementConverter;
import com.dineup.rest.element.converter.RestaurantCommentElementConverter;
import com.dineup.rest.element.converter.RestaurantElementConverter;
import com.dineup.rest.exception.InvalidParameterException;
import com.dineup.util.Converters;
import com.dineup.util.Exceptions;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.core.GenericEntity;

@Singleton
public class RestaurantRestResourceBean implements RestaurantRestResource, HeaderKeys {
    
    @EJB
    private RestaurantDataSource dataSource;

    @EJB
    private ProfileManagerFactory profileManagerFactory;
    
    private ElementContext createElementContext() {
        return ElementContext.newBuilder()
                .profileManagerFactory(profileManagerFactory)
                .build();
    }
    
    @Override
    public Response getRestaurants(ElementConfig elementConfig) {
        try {
            elementConfig.validate();
            List<Restaurant> objects = dataSource.getRestaurants();
            List<RestaurantElement> elements = Converters.convertList(objects, new RestaurantElementConverter(createElementContext(), elementConfig));
            GenericEntity<List<RestaurantElement>> entity = new GenericEntity<List<RestaurantElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return serverError(ex);
        }
    }

    @Override
    public Response getRestaurantComments(ElementConfig elementConfig, Integer restaurantId) {
        if (restaurantId == null) {
            return badRequest("Unspecified restaurantId", /*TODO*/null);
        }
        try {
            elementConfig.validate();
            List<RestaurantComment> objects = dataSource.getRestaurantComments(restaurantId);
            List<RestaurantCommentElement> elements = Converters.convertList(objects, new RestaurantCommentElementConverter(createElementContext(), elementConfig));
            GenericEntity<List<RestaurantCommentElement>> entity = new GenericEntity<List<RestaurantCommentElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return serverError(ex);
        }
    }
    
    @Override
    public Response getCategories(ElementConfig elementConfig, Integer restaurantId) {
        if (restaurantId == null) {
            return badRequest("Unspecified restaurantId", /*TODO*/null);
        }
        try {
            elementConfig.validate();
            List<Category> objects = dataSource.getCategories(restaurantId);
            List<CategoryElement> elements = Converters.convertList(objects, new CategoryElementConverter(createElementContext(), elementConfig));
            GenericEntity<List<CategoryElement>> entity = new GenericEntity<List<CategoryElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return serverError(ex);
        }
    }
    
    @Override
    public Response getFoods(ElementConfig elementConfig, Integer categoryId) {
        if (categoryId == null) {
            return badRequest("Unspecified categoryId", /*TODO*/null);
        }
        try {
            elementConfig.validate();
            List<Food> objects = dataSource.getFoods(categoryId);
            List<FoodElement> elements = Converters.convertList(objects, new FoodElementConverter(createElementContext(), elementConfig));
            GenericEntity<List<FoodElement>> entity = new GenericEntity<List<FoodElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return serverError(ex);
        }
    }
    
    @Override
    public Response getExtras(ElementConfig elementConfig, Integer foodId) {
        if (foodId == null) {
            return badRequest("Unspecified foodId", /*TODO*/null);
        }
        try {
            elementConfig.validate();
            List<Extra> objects = dataSource.getExtras(foodId);
            List<ExtraElement> elements = Converters.convertList(objects, new ExtraElementConverter(createElementContext(), elementConfig));
            GenericEntity<List<ExtraElement>> entity = new GenericEntity<List<ExtraElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return serverError(ex);
        }
    }

    @Override
    public Response getOptions(ElementConfig elementConfig, Integer extraId) {
        if (extraId == null) {
            return badRequest("Unspecified extraId", /*TODO*/null);
        }
        try {
            elementConfig.validate();
            List<Option> objects = dataSource.getOptions(extraId);
            List<OptionElement> elements = Converters.convertList(objects, new OptionElementConverter(createElementContext(), elementConfig));
            GenericEntity<List<OptionElement>> entity = new GenericEntity<List<OptionElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return serverError(ex);
        }
    }
    
    private Response badRequest(String errorMessage, String localizedMessage) {
        if (localizedMessage == null) {
            localizedMessage = errorMessage;
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .header(ERROR_MESSAGE, errorMessage)
                .header(LOCALIZED_ERROR_MESSAGE, localizedMessage)
                .build();
    }
    
    private Response serverError(Exception ex) {
        Throwable rootCause = Exceptions.getRootCause(ex);
        Response.ResponseBuilder builder;
        if (rootCause instanceof InvalidParameterException) {
            builder = Response.status(Response.Status.BAD_REQUEST);
        }
        else {
            builder = Response.serverError();
        }
        return builder
                    .header(ERROR_MESSAGE, rootCause.getMessage())
                    .header(LOCALIZED_ERROR_MESSAGE, rootCause.getLocalizedMessage())
                    .build();
    }

}

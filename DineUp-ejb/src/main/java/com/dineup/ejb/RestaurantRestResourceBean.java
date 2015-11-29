package com.dineup.ejb;

import com.dineup.dom.Category;
import com.dineup.dom.Extra;
import com.dineup.dom.Food;
import com.dineup.dom.Option;
import com.dineup.rest.HeaderKeys;
import com.dineup.dom.Restaurant;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.CategoryElement;
import com.dineup.rest.element.ExtraElement;
import com.dineup.rest.element.FoodElement;
import com.dineup.rest.element.OptionElement;
import com.dineup.rest.element.RestaurantElement;
import javax.ws.rs.core.Response;
import com.dineup.rest.element.converter.CategoryElementConverter;
import com.dineup.rest.element.converter.ExtraElementConverter;
import com.dineup.rest.element.converter.FoodElementConverter;
import com.dineup.rest.element.converter.OptionElementConverter;
import com.dineup.rest.element.converter.RestaurantElementConverter;
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

    @Override
    public Response getRestaurants(ElementConfig elementConfig) {
        try {
            List<Restaurant> objects = dataSource.getRestaurants();
            List<RestaurantElement> elements = Converters.convertList(objects, new RestaurantElementConverter(elementConfig));
            GenericEntity<List<RestaurantElement>> entity = new GenericEntity<List<RestaurantElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return serverError(ex);
        }
    }

    @Override
    public Response getCategories(ElementConfig elementConfig, Integer restaurantId) {
        if (restaurantId == null) {
            return badRequest("Unspeceified restaurantId");
        }
        try {
            List<Category> objects = dataSource.getCategories(restaurantId);
            List<CategoryElement> elements = Converters.convertList(objects, new CategoryElementConverter(elementConfig));
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
            return badRequest("Unspeceified categoryId");
        }
        try {
            List<Food> objects = dataSource.getFoods(categoryId);
            List<FoodElement> elements = Converters.convertList(objects, new FoodElementConverter(elementConfig));
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
            return badRequest("Unspeceified foodId");
        }
        try {
            List<Extra> objects = dataSource.getExtras(foodId);
            List<ExtraElement> elements = Converters.convertList(objects, new ExtraElementConverter(elementConfig));
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
            return badRequest("Unspeceified extraId");
        }
        try {
            List<Option> objects = dataSource.getOptions(extraId);
            List<OptionElement> elements = Converters.convertList(objects, new OptionElementConverter(elementConfig));
            GenericEntity<List<OptionElement>> entity = new GenericEntity<List<OptionElement>>(elements) {};
            return Response.ok(entity).build();
        }
        catch (Exception ex) {
            return serverError(ex);
        }
    }
    
    private Response badRequest(String errorMessage) {
        return Response.status(Response.Status.BAD_REQUEST)
                .header(ERROR_MESSAGE, errorMessage)
                .build();
    }
    
    private Response serverError(Exception ex) {
        return Response.serverError()
                    .header(ERROR_MESSAGE, Exceptions.getRootCause(ex).getMessage())
                    .build();
    }

}

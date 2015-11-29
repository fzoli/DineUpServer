package com.dineup.ejb;

import com.dineup.rest.ElementConfig;
import javax.ws.rs.core.Response;
import javax.ejb.Local;

@Local
public interface RestaurantRestResource {
    public Response getRestaurants(ElementConfig elementConfig);
    public Response getCategories(ElementConfig elementConfig, Integer restaurantId);
    public Response getFoods(ElementConfig elementConfig, Integer categoryId);
    public Response getExtras(ElementConfig elementConfig, Integer foodId);
    public Response getOptions(ElementConfig elementConfig, Integer extraId);
}

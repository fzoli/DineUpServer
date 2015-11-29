package com.dineup.ejb;

import com.dineup.rest.ElementConfig;
import javax.ws.rs.core.Response;
import javax.ejb.Local;

@Local
public interface RestaurantRestResource {
    public Response getTestResponse(ElementConfig elementConfig);
    public Response getRestaurants(ElementConfig elementConfig);
}

package com.dineup.rest.json;

import com.dineup.ejb.rest.RestaurantRestResource;
import com.dineup.rest.BaseResource;
import com.dineup.service.rest.RequestPath;
import com.dineup.service.rest.RestaurantKeys;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path(RequestPath.ROOT_JSON + RequestPath.PATH_CATEGORIES)
public class CategoryResource extends BaseResource {
    
    @Inject
    private RestaurantRestResource resource;
    
    @QueryParam(RestaurantKeys.RESTAURANT_ID)
    private Integer restaurantId;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseByGet() {
        return resource.getCategories(createElementConfig(), restaurantId);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseByPost() {
        return resource.getCategories(createElementConfig(), restaurantId);
    }
    
}

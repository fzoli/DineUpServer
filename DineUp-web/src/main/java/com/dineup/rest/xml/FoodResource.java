package com.dineup.rest.xml;

import com.dineup.ejb.rest.RestaurantRestResource;
import com.dineup.rest.BaseResource;
import com.dineup.rest.RequestPath;
import com.dineup.rest.RestaurantKeys;
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
@Path(RequestPath.ROOT_XML + RequestPath.PATH_FOODS)
public class FoodResource extends BaseResource {
    
    @Inject
    private RestaurantRestResource resource;
    
    @QueryParam(RestaurantKeys.CATEGORY_ID)
    private Integer categoryId;
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getResponseByGet() {
        return resource.getFoods(createElementConfig(), categoryId);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_XML)
    public Response getResponseByPost() {
        return resource.getFoods(createElementConfig(), categoryId);
    }
    
}

package com.dineup.rest.xml;

import com.dineup.ejb.rest.RestaurantRestResource;
import com.dineup.rest.ApiVersion;
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
@Path(RequestPath.ROOT_XML + ApiVersion.ROOT + RequestPath.PATH_EXTRAS)
public class ExtraResource extends BaseResource {
    
    @Inject
    private RestaurantRestResource resource;
    
    @QueryParam(RestaurantKeys.FOOD_ID)
    private Integer foodId;
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getResponseByGet() {
        return resource.getExtras(createElementConfig(), foodId);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_XML)
    public Response getResponseByPost() {
        return resource.getExtras(createElementConfig(), foodId);
    }
    
}

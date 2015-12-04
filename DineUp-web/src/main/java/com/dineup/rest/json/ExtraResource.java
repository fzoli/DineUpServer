package com.dineup.rest.json;

import com.dineup.ejb.rest.RestaurantRestResource;
import com.dineup.rest.ElementConfig;
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
@Path(RequestPath.ROOT_JSON + RequestPath.PATH_EXTRAS)
public class ExtraResource {
    
    @Inject
    private RestaurantRestResource resource;
    
    @QueryParam(ElementConfig.Keys.LANGUAGE_CODE)
    private String languageCode;

    @QueryParam(ElementConfig.Keys.WITH_NESTED_OBJECTS)
    private Boolean withNestedObjects;
    
    @QueryParam(RestaurantKeys.FOOD_ID)
    private Integer foodId;
    
    private ElementConfig createElementConfig() {
        return ElementConfig.newBuilder()
                .languageCode(languageCode)
                .withNestedObjects(withNestedObjects)
                .build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseByGet() {
        return resource.getExtras(createElementConfig(), foodId);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseByPost() {
        return resource.getExtras(createElementConfig(), foodId);
    }
    
}

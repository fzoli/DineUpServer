package com.dineup.rest.json;

import com.dineup.ejb.RestaurantRestResource;
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
@Path(RequestPath.ROOT_JSON + RequestPath.PATH_FOODS)
public class FoodResource {
    
    @Inject
    private RestaurantRestResource resource;
    
    @QueryParam(ElementConfig.Keys.LANGUAGE_CODE)
    private String languageCode;

    @QueryParam(ElementConfig.Keys.WITH_NESTED_OBJECTS)
    private Boolean withNestedObjects;
    
    @QueryParam(RestaurantKeys.CATEGORY_ID)
    private Integer categoryId;
    
    private ElementConfig createElementConfig() {
        return ElementConfig.newBuilder()
                .languageCode(languageCode)
                .withNestedObjects(withNestedObjects)
                .build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseByGet() {
        return resource.getFoods(createElementConfig(), categoryId);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseByPost() {
        return resource.getFoods(createElementConfig(), categoryId);
    }
    
}

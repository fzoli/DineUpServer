package com.dineup.rest.xml;

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
@Path(RequestPath.ROOT_XML + RequestPath.PATH_RESTAURANT_COMMENTS)
public class RestaurantCommentResource {
    
    @Inject
    private RestaurantRestResource resource;
    
    @QueryParam(ElementConfig.Keys.FACEBOOK_ACCESS_TOKEN)
    private String facebookAccessToken;
    
    @QueryParam(ElementConfig.Keys.GOOGLE_ACCESS_TOKEN)
    private String googleAccessToken;
    
    @QueryParam(ElementConfig.Keys.LANGUAGE_CODE)
    private String languageCode;
    
    @QueryParam(ElementConfig.Keys.WITH_NESTED_OBJECTS)
    private Boolean withNestedObjects;
    
    @QueryParam(RestaurantKeys.RESTAURANT_ID)
    private Integer restaurantId;
    
    private ElementConfig createElementConfig() {
        return ElementConfig.newBuilder()
                .languageCode(languageCode)
                .withNestedObjects(withNestedObjects)
                .facebookAccessToken(facebookAccessToken)
                .googleAccessToken(googleAccessToken)
                .build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getResponseByGet() {
        return resource.getRestaurantComments(createElementConfig(), restaurantId);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_XML)
    public Response getResponseByPost() {
        return resource.getRestaurantComments(createElementConfig(), restaurantId);
    }
    
}

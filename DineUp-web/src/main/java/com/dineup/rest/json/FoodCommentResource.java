package com.dineup.rest.json;

import com.dineup.ejb.rest.RestaurantRestResource;
import com.dineup.rest.ApiVersion;
import com.dineup.rest.BaseResource;
import com.dineup.service.request.CommentRequest;
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
@Path(RequestPath.ROOT_JSON + ApiVersion.ROOT)
public class FoodCommentResource extends BaseResource {
    
    @Inject
    private RestaurantRestResource resource;
    
    @QueryParam(RestaurantKeys.FOOD_ID)
    private Integer foodId;
    
    @QueryParam(RestaurantKeys.MESSAGE)
    private String message;
    
    @QueryParam(RestaurantKeys.RATING)
    private Double rating;
    
    @GET
    @Path(RequestPath.PATH_FOOD_COMMENTS)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseByGet() {
        return resource.getFoodComments(createElementConfig(), foodId);
    }
    
    @POST
    @Path(RequestPath.PATH_FOOD_COMMENTS)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseByPost() {
        return resource.getFoodComments(createElementConfig(), foodId);
    }
    
    @GET
    @Path(RequestPath.PATH_ADD_FOOD_COMMENT)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCommentByGet() {
        return addComment();
    }
    
    @POST
    @Path(RequestPath.PATH_ADD_FOOD_COMMENT)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCommentByPost() {
        return addComment();
    }
    
    private Response addComment() {
        return resource.addFoodComment(createElementConfig(), CommentRequest.newBuilder()
                .id(foodId)
                .publicProfile(true)
                .message(message)
                .rating(rating)
                .build());
    }
    
}

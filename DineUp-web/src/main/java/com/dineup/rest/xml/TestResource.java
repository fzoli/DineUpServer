package com.dineup.rest.xml;

import javax.ws.rs.core.Response;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.dineup.ejb.RestaurantRestResource;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.RequestPath;
import javax.ws.rs.POST;

@RequestScoped
@Path(RequestPath.ROOT_XML + RequestPath.PATH_TEST)
public class TestResource {
    
    @Inject
    private RestaurantRestResource test;
    
    @QueryParam(ElementConfig.Keys.LANGUAGE_CODE)
    private String languageCode;

    @QueryParam(ElementConfig.Keys.WITH_NESTED_OBJECTS)
    private Boolean withNestedObjects;
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getResponseByGet() {
        return test.getTestResponse(createElementConfig());
    }
    
    @POST
    @Produces(MediaType.APPLICATION_XML)
    public Response getResponseByPost() {
        return test.getTestResponse(createElementConfig());
    }
    
    private ElementConfig createElementConfig() {
        return ElementConfig.newBuilder()
                .languageCode(languageCode)
                .withNestedObjects(withNestedObjects)
                .build();
    }
    
}
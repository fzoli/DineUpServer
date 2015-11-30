package com.dineup.rest.json;

import com.dineup.rest.RequestPath;
import com.dineup.rest.element.ServiceElement;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(RequestPath.ROOT_JSON + RequestPath.PATH_SERVICE)
public class ServiceResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseByGet() {
        return createResponse();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseByPost() {
        return createResponse();
    }
    
    private Response createResponse() {
        return Response.ok(new ServiceElement()).build();
    }
    
}

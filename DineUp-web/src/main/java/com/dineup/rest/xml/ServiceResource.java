package com.dineup.rest.xml;

import com.dineup.rest.RequestPath;
import com.dineup.rest.element.ServiceElement;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(RequestPath.ROOT_XML + RequestPath.PATH_SERVICE)
public class ServiceResource {
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getResponseByGet() {
        return createResponse();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_XML)
    public Response getResponseByPost() {
        return createResponse();
    }
    
    private Response createResponse() {
        return Response.ok(new ServiceElement()).build();
    }
    
}

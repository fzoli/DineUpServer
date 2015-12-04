package com.dineup.rest.xml;

import com.dineup.ejb.rest.ServiceRestResource;
import com.dineup.rest.RequestPath;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(RequestPath.ROOT_XML + RequestPath.PATH_SERVICE)
public class ServiceResource {
    
    @Inject
    private ServiceRestResource resource;
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getResponseByGet() {
        return resource.getService();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_XML)
    public Response getResponseByPost() {
        return resource.getService();
    }
    
}

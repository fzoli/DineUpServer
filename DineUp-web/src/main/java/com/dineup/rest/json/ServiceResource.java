package com.dineup.rest.json;

import com.dineup.ejb.rest.ServiceRestResource;
import com.dineup.rest.ApiVersion;
import com.dineup.service.rest.RequestPath;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path(RequestPath.ROOT_JSON + ApiVersion.ROOT + RequestPath.PATH_SERVICE)
public class ServiceResource {
    
    @Inject
    private ServiceRestResource resource;
    
    @PathParam(ApiVersion.KEY)
    private String apiVersion;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseByGet() {
        return resource.getService(apiVersion);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseByPost() {
        return resource.getService(apiVersion);
    }
    
}

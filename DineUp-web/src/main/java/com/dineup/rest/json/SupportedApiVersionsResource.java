package com.dineup.rest.json;

import com.dineup.ejb.rest.ServiceRestResource;
import com.dineup.service.rest.RequestPath;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path(RequestPath.ROOT_JSON + RequestPath.PATH_SUPPORTED_API_VERSIONS)
public class SupportedApiVersionsResource {
    
    @Inject
    private ServiceRestResource resource;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseByGet() {
        return resource.getSupportedApiVersions();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseByPost() {
        return resource.getSupportedApiVersions();
    }
    
}

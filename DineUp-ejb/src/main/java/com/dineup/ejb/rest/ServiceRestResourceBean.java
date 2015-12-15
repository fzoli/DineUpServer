package com.dineup.ejb.rest;

import com.dineup.service.element.ServiceElement;
import com.dineup.service.ServiceConfig;
import java.util.List;
import javax.ejb.Singleton;
import javax.ws.rs.core.Response;

@Singleton
public class ServiceRestResourceBean implements ServiceRestResource {
    
    @Override
    public Response getSupportedApiVersions() {
        List<String> versions = ServiceConfig.SUPPORTED_API_VERSIONS;
        return Response.ok(versions).build();
    }
    
    @Override
    public Response getService(String apiVersion) {
        ServiceElement serviceElement = new ServiceElement();
        serviceElement.apiVersion = ServiceConfig.API_VERSION;
        serviceElement.clientUpToDate = true; // TODO
        return Response.ok(serviceElement).build();
    }
    
}

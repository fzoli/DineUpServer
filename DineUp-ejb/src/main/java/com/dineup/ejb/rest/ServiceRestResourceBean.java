package com.dineup.ejb.rest;

import com.dineup.rest.element.ServiceElement;
import javax.ejb.Singleton;
import javax.ws.rs.core.Response;

@Singleton
public class ServiceRestResourceBean implements ServiceRestResource {

    @Override
    public Response getService() {
        return Response.ok(new ServiceElement()).build();
    }
    
}

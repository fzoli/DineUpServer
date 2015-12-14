package com.dineup.ejb.rest;

import com.dineup.BuildConfig;
import com.dineup.service.element.ServiceElement;
import javax.ejb.Singleton;
import javax.ws.rs.core.Response;

@Singleton
public class ServiceRestResourceBean implements ServiceRestResource {

    @Override
    public Response getService() {
        ServiceElement serviceElement = new ServiceElement();
        serviceElement.protocolVersion = BuildConfig.SERVICE_PROTOCOL_VERSION;
        return Response.ok(serviceElement).build();
    }
    
}

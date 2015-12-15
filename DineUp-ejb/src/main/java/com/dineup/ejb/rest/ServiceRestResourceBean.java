package com.dineup.ejb.rest;

import com.dineup.service.element.ServiceElement;
import com.dineup.service.ServiceConfig;
import com.dineup.util.VersionNumber;
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
        int versionCompareResult = VersionNumber.versionCompare(ServiceConfig.API_VERSION, apiVersion);
        serviceElement.clientUpToDate =  versionCompareResult <= 0;
        return Response.ok(serviceElement).build();
    }
    
}

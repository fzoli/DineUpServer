package com.dineup.ejb.rest;

import javax.ejb.Local;
import javax.ws.rs.core.Response;

@Local
public interface ServiceRestResource {
    public Response getSupportedApiVersions();
    public Response getService(String apiVersion);
}

package com.dineup;

public final class BuildConfig {

    public static final String SOAP_SERVICE_NAME = "${dineup.ejb.soap.service_name}";
    public static final String WEB_CONTEXT_ROOT = "${dineup.web.context_root}";
    public static final String WEB_REST_ROOT = "${dineup.web.rest.root}";
    
    public static final String GOOGLE_API_KEY = "${dineup.google.api_key}";

    private BuildConfig() {
    }
    
}

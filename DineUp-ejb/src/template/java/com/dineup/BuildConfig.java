package com.dineup;

public final class BuildConfig {

    public static final String SOAP_SERVICE_NAME = "${dineup.ejb.soap.service_name}";
    public static final String WEB_CONTEXT_ROOT = "${dineup.web.context_root}";
    public static final String WEB_REST_ROOT = "${dineup.web.rest.root}";
    
    public static final String GOOGLE_API_KEY = "${dineup.google.api_key}";
    
    /**
     * The protocol version.
     * The client app have to support exactly the same version in order to communicate with the server.
     * Incremented when an existing resource message changes.
     */
    public static final int SERVICE_PROTOCOL_VERSION = ${dineup.service.protocol_version};

    private BuildConfig() {
    }
    
}

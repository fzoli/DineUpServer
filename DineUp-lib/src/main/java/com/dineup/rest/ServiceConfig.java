package com.dineup.rest;

public interface ServiceConfig {
    /**
     * The protocol version.
     * The client app have to support exactly the same version in order to communicate with the server.
     * Incremented when an existing resource message changes.
     */
    int PROTOCOL_VERSION = 1;
}

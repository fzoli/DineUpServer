package com.dineup.api;

import com.dineup.api.exception.DetailedException;
import com.dineup.api.service.DineUpApiFactoryHandler;
import javax.ws.rs.client.Client;

public final class DineUpApiFactory {

    private final DineUpApiFactoryHandler handler;
    
    /**
     * Constructor.
     * @param client the client that executes the requests and authenticates the server
     * @param targetConfig the target configuration that points to the service
     */
    public DineUpApiFactory(Client client, TargetConfig targetConfig) {
        handler = new DineUpApiFactoryHandler(client, targetConfig);
    }
    
    /**
     * Creates the API with the requested version.
     * Note: Only the latest API supports all the requests.
     * Not supported requests will throw {@link DetailedException}.
     * @see #createInstance()
     * @param version the requested version
     * @return the API with the requested version
     */
    public DineUpApi createInstance(ApiVersion version) {
        return handler.createInstance(version);
    }
    
    /**
     * Creates the latest usable API that the server supports.
     * @return the latest usable API
     * @throws DetailedException if failed to communicate
     * with the server or the server does not support the API
     */
    public DineUpApi createInstance() throws DetailedException {
        return handler.createInstance();
    }
    
}

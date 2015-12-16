package com.dineup.api;

import com.dineup.api.exception.DetailedException;

public interface DineUpApiFactory {
    
    /**
     * Creates the API with the requested version.
     * Note: Only the latest API supports all the requests.
     * Not supported requests will throw {@link DetailedException}.
     * @see #createInstance()
     * @param version the requested version
     * @return the API with the requested version
     */
    public DineUpApi createInstance(ApiVersion version);
    
    /**
     * Creates the latest usable API that the server supports.
     * @return the latest usable API
     * @throws DetailedException if failed to communicate
     * with the server or the server does not support the API
     */
    public DineUpApi createInstance() throws DetailedException;
    
}

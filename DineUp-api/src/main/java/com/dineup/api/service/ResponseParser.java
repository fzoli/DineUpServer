package com.dineup.api.service;

import javax.ws.rs.core.Response;

public interface ResponseParser<T> {
    public T parseResponse(Response response);
}

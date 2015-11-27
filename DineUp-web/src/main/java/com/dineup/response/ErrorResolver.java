package com.dineup.response;

public interface ErrorResolver {
    public Error resolveError(Exception exception);
}

package com.dineup.response;

public interface ErrorResolver {
    public Result resolveError(Exception exception);
}

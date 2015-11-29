package com.dineup.response;

public interface ErrorResolver {
    public ErrorBundle resolveError(Exception exception);
}

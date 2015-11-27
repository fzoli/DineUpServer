package com.dineup.response;

public class Error {
    
    private final Result result;
    private final Throwable throwable;

    public Error(Result result, Throwable throwable) {
        this.result = result;
        this.throwable = throwable;
    }

    public Result getResult() {
        return result;
    }

    public Throwable getThrowable() {
        return throwable;
    }
    
}

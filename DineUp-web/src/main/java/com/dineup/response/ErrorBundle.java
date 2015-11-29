package com.dineup.response;

public class ErrorBundle {
    
    private final Result result;
    private final Throwable throwable;

    public ErrorBundle(Result result, Throwable throwable) {
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

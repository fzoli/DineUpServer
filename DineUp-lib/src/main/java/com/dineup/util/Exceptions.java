package com.dineup.util;

public class Exceptions {

    public static Throwable getRootCause(Throwable throwable) {
        if (throwable == null) {
            return null;
        }
        if (throwable.getCause() != null) {
            return getRootCause(throwable.getCause());
        }
        return throwable;
    }
    
    private Exceptions() {
    }
    
}

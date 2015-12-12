package com.dineup.util;

public interface Converter<In, Out> {
    public Out convert(In obj);
}

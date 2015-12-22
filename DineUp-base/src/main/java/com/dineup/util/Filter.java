package com.dineup.util;

public interface Filter<T> {
    public boolean isFiltered(T object);
}

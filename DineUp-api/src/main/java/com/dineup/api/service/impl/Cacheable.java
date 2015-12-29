package com.dineup.api.service.impl;

import com.dineup.api.DineUpCache;

public interface Cacheable<T> {
    public DineUpCache<T> getCache();
}

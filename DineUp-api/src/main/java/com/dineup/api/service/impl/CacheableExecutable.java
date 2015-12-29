package com.dineup.api.service.impl;

public interface CacheableExecutable<T> extends Executable<T>, Cacheable<T> {
    
}

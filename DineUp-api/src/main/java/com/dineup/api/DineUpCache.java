package com.dineup.api;

import java.util.Date;

public interface DineUpCache<T> {
    
    public interface CacheResult<T> {
        public T data();
        public Date lastModified();
        public String apiVersion();
        public String languageCode();
    }
    
    public interface CacheRequest<T> {
        public T data();
        public String apiVersion();
        public String languageCode();
    }
    
    /**
     * @return null or the cache result
     * @throws java.lang.Exception if failed to get the result
     */
    public CacheResult<T> get() throws Exception;
    
    public void set(CacheRequest<T> request);
    
    public void delete();
    
}

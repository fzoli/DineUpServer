package com.dineup.api;

import java.util.Date;

public interface DineUpCache<T> {
    
    public interface CacheResult<T> {
        public T data();
        public Date lastModified();
        public String apiVersion();
    }
    
    /**
     * @return null or the cache result
     * @throws java.lang.Exception if failed to get the result
     */
    public CacheResult<T> get() throws Exception;
    
    /**
     * @param data the data to store or null to delete the cache
     * @param apiVersion api version of the data
     * @param languageCode language of the data
     * @throws java.lang.Exception if failed to set the result
     */
    public void set(T data, String apiVersion, String languageCode) throws Exception;
    
}

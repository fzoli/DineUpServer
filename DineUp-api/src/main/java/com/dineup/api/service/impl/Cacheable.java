package com.dineup.api.service.impl;

import com.dineup.api.DineUpCache;
import com.dineup.api.DineUpCacheManager;
import java.util.Date;
import com.dineup.api.service.dom.ServiceModificationDates;

public interface Cacheable<T> {
    public DineUpCache<T> getCache(DineUpCacheManager cacheManager);
    public Date getModificationDate(ServiceModificationDates dates);
}

package com.dineup.ejb;

import com.dineup.BuildConfig;

public interface SoapKeys {
    String SERVICE_NAME = BuildConfig.CONTEXT_ROOT + "/soap";
    String NAMESPACE = "http://soap.dineup.com";
}

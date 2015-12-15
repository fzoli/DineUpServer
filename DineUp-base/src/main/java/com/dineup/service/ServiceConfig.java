package com.dineup.service;

import java.util.Arrays;
import java.util.List;

public interface ServiceConfig {
    String API_VERSION = "v1.0";
    List<String> SUPPORTED_API_VERSIONS = Arrays.asList(API_VERSION);
}

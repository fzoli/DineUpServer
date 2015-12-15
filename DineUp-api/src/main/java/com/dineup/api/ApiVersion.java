package com.dineup.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum ApiVersion {
    
    V1_0("v1.0")
    
    ;

    private final String version;
    
    private ApiVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
    
    public static List<ApiVersion> getDecrescentVersions() {
        List<ApiVersion> versions = getAscendantVersions();
        Collections.reverse(versions);
        return versions;
    }
    
    private static List<ApiVersion> getAscendantVersions() {
        List<ApiVersion> versions = new ArrayList<>();
        versions.addAll(Arrays.asList(ApiVersion.values()));
        return versions;
        
    }
    
}

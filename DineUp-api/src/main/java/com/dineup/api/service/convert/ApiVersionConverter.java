package com.dineup.api.service.convert;

import com.dineup.api.ApiVersion;
import com.dineup.util.Converter;

public final class ApiVersionConverter implements Converter<String, ApiVersion> {

    private static final ApiVersionConverter INSTANCE = new ApiVersionConverter();

    public static ApiVersionConverter getInstance() {
        return INSTANCE;
    }

    private ApiVersionConverter() {
    }
    
    @Override
    public ApiVersion convert(String obj) {
        for (ApiVersion version : ApiVersion.values()) {
            if (version.getVersion().equalsIgnoreCase(obj)) {
                return version;
            }
        }
        return null;
    }

}

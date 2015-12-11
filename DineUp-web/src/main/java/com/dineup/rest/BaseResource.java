package com.dineup.rest;

import com.dineup.service.ElementConfig;
import com.dineup.service.rest.ElementConfigKeys;
import javax.ws.rs.QueryParam;

public class BaseResource {
    
    @QueryParam(ElementConfigKeys.FACEBOOK_ACCESS_TOKEN)
    private String facebookAccessToken;
    
    @QueryParam(ElementConfigKeys.GOOGLE_ACCESS_TOKEN)
    private String googleAccessToken;
    
    @QueryParam(ElementConfigKeys.LANGUAGE_CODE)
    private String languageCode;
    
    @QueryParam(ElementConfigKeys.WITH_NESTED_OBJECTS)
    private Boolean withNestedObjects;
    
    @QueryParam(ElementConfigKeys.LATITUDE)
    private Double latitude;
    
    @QueryParam(ElementConfigKeys.LONGITUDE)
    private Double longitude;
    
    public ElementConfig createElementConfig() {
        return ElementConfig.newBuilder()
                .languageCode(languageCode)
                .withNestedObjects(withNestedObjects)
                .latitude(latitude)
                .longitude(longitude)
                .facebookAccessToken(facebookAccessToken)
                .googleAccessToken(googleAccessToken)
                .build();
    }
    
}

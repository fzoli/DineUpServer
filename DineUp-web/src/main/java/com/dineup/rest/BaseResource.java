package com.dineup.rest;

import javax.ws.rs.QueryParam;

public class BaseResource {
    
    @QueryParam(ElementConfig.Keys.FACEBOOK_ACCESS_TOKEN)
    private String facebookAccessToken;
    
    @QueryParam(ElementConfig.Keys.GOOGLE_ACCESS_TOKEN)
    private String googleAccessToken;
    
    @QueryParam(ElementConfig.Keys.LANGUAGE_CODE)
    private String languageCode;
    
    @QueryParam(ElementConfig.Keys.WITH_NESTED_OBJECTS)
    private Boolean withNestedObjects;
    
    @QueryParam(ElementConfig.Keys.LATITUDE)
    private Double latitude;
    
    @QueryParam(ElementConfig.Keys.LONGITUDE)
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

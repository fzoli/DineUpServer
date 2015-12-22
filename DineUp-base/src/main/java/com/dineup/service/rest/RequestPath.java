package com.dineup.service.rest;

public interface RequestPath {
    String ROOT_JSON = "/json";
    String ROOT_XML = "/xml";
    
    String PATH_SUPPORTED_API_VERSIONS = "/supportedApiVersions";
    String PATH_SERVICE = "/service";

    String PATH_RESTAURANTS = "/restaurants";
    String PATH_RESTAURANT_COMMENTS = "/restaurantComments";
    String PATH_FOOD_COMMENTS = "/foodComments";
    String PATH_CATEGORIES = "/categories";
    String PATH_FOODS = "/foods";
    String PATH_EXTRAS = "/extras";
    String PATH_OPTIONS = "/options";
    
    String PATH_GOOGLE_PROFILE_PHOTO = "/GoogleProfilePhoto";
}

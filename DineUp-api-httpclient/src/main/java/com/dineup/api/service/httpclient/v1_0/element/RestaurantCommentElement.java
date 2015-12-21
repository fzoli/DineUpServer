package com.dineup.api.service.httpclient.v1_0.element;

import java.io.Serializable;
import java.util.Date;
import com.dineup.api.dom.Comment;

public class RestaurantCommentElement implements Comment, Serializable {

    public String languageCode;
    public String message;
    public ProfileElement profile;
    public double rating;
    public Date time;

    public RestaurantCommentElement() {
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public ProfileElement getProfile() {
        return profile;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public Date getTime() {
        return time;
    }
    
    @Override
    public String toString() {
        return String.format("RestaurantComment(rating=%s)", rating);
    }
    
}

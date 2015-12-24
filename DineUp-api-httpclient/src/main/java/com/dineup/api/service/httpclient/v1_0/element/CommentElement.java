package com.dineup.api.service.httpclient.v1_0.element;

import java.io.Serializable;
import java.util.Date;
import com.dineup.api.dom.Comment;

public class CommentElement implements Comment, Serializable {

    public MessageElement message;
    public ProfileElement profile;
    public double rating;
    public Date time;

    public CommentElement() {
    }

    @Override
    public MessageElement getMessage() {
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
        return String.format("Comment(rating=%s)", rating);
    }
    
}

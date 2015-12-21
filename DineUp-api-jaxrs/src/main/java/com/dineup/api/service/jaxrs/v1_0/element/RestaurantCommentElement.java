package com.dineup.api.service.jaxrs.v1_0.element;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.dineup.api.dom.Comment;

@XmlRootElement(name = "comment")
public class RestaurantCommentElement implements Comment, Serializable {
    
    @XmlElement
    public String languageCode;
    
    @XmlElement
    public String message;
    
    @XmlElement
    public ProfileElement profile;
    
    @XmlElement
    public double rating;
    
    @XmlElement
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

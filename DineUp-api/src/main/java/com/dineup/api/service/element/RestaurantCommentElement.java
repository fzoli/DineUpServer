package com.dineup.api.service.element;

import com.dineup.api.dom.RestaurantComment;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comment")
public class RestaurantCommentElement implements RestaurantComment {
    
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

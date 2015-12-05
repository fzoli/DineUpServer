package com.dineup.rest.element;

import com.dineup.dom.RestaurantComment;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.converter.ProfileElementConverter;
import com.dineup.util.Converters;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comment")
public class RestaurantCommentElement {

    private ElementConfig elementConfig;
    private RestaurantComment comment;
    
    public RestaurantCommentElement(ElementConfig elementConfig, RestaurantComment comment) {
        this.elementConfig = elementConfig;
        this.comment = comment;
    }

    public RestaurantCommentElement() {
    }
    
    @XmlElement
    public String getMessage() {
        return comment.getMessage();
    }
    
    @XmlElement
    public String getLanguageCode() {
        return comment.getLanguageCode();
    }
    
    @XmlElement
    public ProfileElement getProfile() {
        if (!comment.isProfilePublic()) {
            return null;
        }
        return Converters.convert(comment.getProfile(), new ProfileElementConverter(elementConfig));
    }
    
    @XmlElement
    public double getRating() {
        return comment.getRating();
    }
    
    @XmlElement
    public Date getTime() {
        return comment.getTime();
    }
    
}
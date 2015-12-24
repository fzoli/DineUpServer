package com.dineup.service.element;

import com.dineup.service.ElementContext;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.converter.ProfileElementConverter;
import com.dineup.util.Converters;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.dineup.dom.Comment;
import com.dineup.service.element.converter.MessageElementConverter;

@XmlRootElement(name = "comment")
public class CommentElement {

    private ElementContext elementContext;
    private ElementConfig elementConfig;
    private Comment comment;
    
    public CommentElement(ElementContext elementContext, ElementConfig elementConfig, Comment comment) {
        this.elementContext = elementContext;
        this.elementConfig = elementConfig;
        this.comment = comment;
    }

    public CommentElement() {
    }
    
    @XmlElement
    public MessageElement getMessage() {
        return Converters.convert(comment.getMessage(), new MessageElementConverter(elementContext, elementConfig));
    }
    
    @XmlElement
    public ProfileElement getProfile() {
        if (!comment.isProfilePublic()) {
            return null;
        }
        return Converters.convert(comment.getProfile(), new ProfileElementConverter(elementContext, elementConfig));
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

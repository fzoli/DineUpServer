package com.dineup.service.element.converter;

import com.dineup.service.ElementContext;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.CommentElement;
import com.dineup.dom.Comment;

public class CommentElementConverter extends BaseElementConverter<Comment, CommentElement> {

    public CommentElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    protected CommentElement safeConvert(Comment comment) {
        return new CommentElement(elementContext, elementConfig, comment);
    }
    
}

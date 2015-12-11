package com.dineup.service.element.converter;

import com.dineup.service.ElementContext;
import com.dineup.dom.RestaurantComment;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.RestaurantCommentElement;

public class RestaurantCommentElementConverter extends BaseElementConverter<RestaurantComment, RestaurantCommentElement> {

    public RestaurantCommentElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    protected RestaurantCommentElement safeConvert(RestaurantComment comment) {
        return new RestaurantCommentElement(elementContext, elementConfig, comment);
    }
    
}

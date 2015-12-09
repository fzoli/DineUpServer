package com.dineup.rest.element.converter;

import com.dineup.rest.ElementContext;
import com.dineup.dom.RestaurantComment;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.RestaurantCommentElement;

public class RestaurantCommentElementConverter extends BaseElementConverter<RestaurantComment, RestaurantCommentElement> {

    public RestaurantCommentElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    protected RestaurantCommentElement safeConvert(RestaurantComment comment) {
        return new RestaurantCommentElement(elementContext, elementConfig, comment);
    }
    
}

package com.dineup.rest.element.converter;

import com.dineup.dom.Category;
import com.dineup.rest.ElementContext;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.CategoryElement;

public class CategoryElementConverter extends BaseElementConverter<Category, CategoryElement> {

    public CategoryElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    public CategoryElement safeConvert(Category category) {
        return new CategoryElement(elementContext, elementConfig, category);
    }
    
}

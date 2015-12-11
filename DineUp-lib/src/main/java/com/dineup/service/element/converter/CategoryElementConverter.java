package com.dineup.service.element.converter;

import com.dineup.dom.Category;
import com.dineup.service.ElementContext;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.CategoryElement;

public class CategoryElementConverter extends BaseElementConverter<Category, CategoryElement> {

    public CategoryElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    public CategoryElement safeConvert(Category category) {
        return new CategoryElement(elementContext, elementConfig, category);
    }
    
}

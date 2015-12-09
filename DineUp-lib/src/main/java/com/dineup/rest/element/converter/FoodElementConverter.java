package com.dineup.rest.element.converter;

import com.dineup.rest.ElementContext;
import com.dineup.dom.Food;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.FoodElement;

public class FoodElementConverter extends BaseElementConverter<Food, FoodElement> {

    public FoodElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    public FoodElement safeConvert(Food food) {
        return new FoodElement(elementContext, elementConfig, food);
    }
    
}

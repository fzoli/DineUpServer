package com.dineup.service.element.converter;

import com.dineup.service.ElementContext;
import com.dineup.dom.Food;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.FoodElement;

public class FoodElementConverter extends BaseElementConverter<Food, FoodElement> {

    public FoodElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    public FoodElement safeConvert(Food food) {
        return new FoodElement(elementContext, elementConfig, food);
    }
    
}

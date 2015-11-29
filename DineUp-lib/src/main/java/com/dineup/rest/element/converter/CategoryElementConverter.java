package com.dineup.rest.element.converter;

import com.dineup.dom.Category;
import com.dineup.dom.CategoryLocale;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.CategoryElement;
import com.dineup.util.Converters;

public class CategoryElementConverter extends BaseLocalizedElementConverter<Category, CategoryLocale, CategoryElement> {

    public CategoryElementConverter(ElementConfig elementConfig) {
        super(elementConfig);
    }

    @Override
    public CategoryElement convert(Category o) {
        if (o == null) {
            return null;
        }
        CategoryElement e = new CategoryElement();
        CategoryLocale l = getLocale(o);
        if (l != null) {
            e.name = l.getName();
        }
        if (elementConfig.withNestedObjects()) {
            e.foods = Converters.convertList(o.getFoods(), new FoodElementConverter(elementConfig));
        }
        e.id = o.getId();
        e.photoUrl = o.getPhotoUrl();
        return e;
    }
    
}

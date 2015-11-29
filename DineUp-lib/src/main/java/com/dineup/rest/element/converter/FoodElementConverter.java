package com.dineup.rest.element.converter;

import com.dineup.dom.Food;
import com.dineup.dom.FoodLocale;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.FoodElement;
import com.dineup.util.Converters;

public class FoodElementConverter extends BaseLocalizedElementConverter<Food, FoodLocale, FoodElement> {

    public FoodElementConverter(ElementConfig elementConfig) {
        super(elementConfig);
    }

    @Override
    public FoodElement convert(Food o) {
        if (o == null) {
            return null;
        }
        FoodElement e = new FoodElement();
        FoodLocale l = getLocale(o);
        if (l != null) {
            e.name = l.getName();
            e.description = l.getDescription();
        }
        if (elementConfig.withNestedObjects()) {
            e.extras = Converters.convertList(o.getExtras(), new ExtraElementConverter(elementConfig));
        }
        e.prices = Converters.convertList(o.getPrices(), new PriceElementConverter(elementConfig));
        e.id = o.getId();
        e.photoUrl = o.getPhotoUrl();
        return e;
    }
    
}

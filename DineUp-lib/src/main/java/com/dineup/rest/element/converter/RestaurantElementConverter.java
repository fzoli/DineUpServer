package com.dineup.rest.element.converter;

import com.dineup.dom.Restaurant;
import com.dineup.dom.RestaurantLocale;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.RestaurantElement;
import com.dineup.util.Converters;

public class RestaurantElementConverter extends BaseLocalizedElementConverter<Restaurant, RestaurantLocale, RestaurantElement> {

    public RestaurantElementConverter(ElementConfig elementConfig) {
        super(elementConfig);
    }
    
    @Override
    public RestaurantElement convert(Restaurant o) {
        if (o == null) {
            return null;
        }
        RestaurantElement e = new RestaurantElement();
        RestaurantLocale l = getLocale(o);
        if (l != null) {
            e.name = l.getName();
            e.description = l.getDescription();
            e.openHours = l.getOpenHours();
        }
        if (elementConfig.withNestedObjects()) {
            e.categories = Converters.convertList(o.getCategories(), new CategoryElementConverter(elementConfig));
        }
        e.address = o.getAddress();
        e.coordinate = Converters.convert(o.getCoordinate(), new CoordinateElementConverter(elementConfig));
        e.defaultCurrency = o.getDefaultCurrency();
        e.id = o.getId();
        e.photoUrl = o.getPhotoUrl();
        e.rating = o.getRating();
        e.type = o.getType();
        return e;
    }
    
}

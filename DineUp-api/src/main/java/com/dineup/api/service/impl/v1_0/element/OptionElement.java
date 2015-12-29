package com.dineup.api.service.impl.v1_0.element;

import com.dineup.api.dom.Price;
import com.dineup.api.service.dom.ServiceOption;
import com.dineup.util.Lists;
import java.io.Serializable;
import java.util.List;

public class OptionElement implements ServiceOption, Serializable {

    public int id;
    public int restaurantId;
    public String languageCode;
    public String name;
    public List<PriceElement> prices;
    
    public OptionElement() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getRestaurantId() {
        return restaurantId;
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Price> getPrices() {
        return Lists.convert(prices);
    }
    
    @Override
    public String toString() {
        return String.format("Option(id=%s)", id);
    }
    
}

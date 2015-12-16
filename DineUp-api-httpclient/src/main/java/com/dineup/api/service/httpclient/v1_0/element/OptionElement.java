package com.dineup.api.service.httpclient.v1_0.element;

import com.dineup.api.dom.Option;
import com.dineup.api.dom.Price;
import com.dineup.util.Lists;
import java.io.Serializable;
import java.util.List;

public class OptionElement implements Option, Serializable {

    public int id;
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

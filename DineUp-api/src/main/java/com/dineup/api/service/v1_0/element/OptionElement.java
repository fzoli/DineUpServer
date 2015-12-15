package com.dineup.api.service.v1_0.element;

import com.dineup.api.dom.Option;
import com.dineup.api.dom.Price;
import com.dineup.util.Lists;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class OptionElement implements Option, Serializable {

    @XmlAttribute
    public int id;
    
    @XmlElement
    public String languageCode;
    
    @XmlElement
    public String name;
    
    @XmlElement
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

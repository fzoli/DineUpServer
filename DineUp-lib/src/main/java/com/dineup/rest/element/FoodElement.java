package com.dineup.rest.element;

import com.dineup.dom.Food;
import com.dineup.dom.FoodLocale;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.converter.ExtraElementConverter;
import com.dineup.rest.element.converter.PriceElementConverter;
import com.dineup.util.Converters;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "food")
public class FoodElement {
    
    private ElementConfig elementConfig;
    private Food food;
    
    public FoodElement() {
    }

    public FoodElement(ElementConfig elementConfig, Food food) {
        this.elementConfig = elementConfig;
        this.food = food;
    }

    private FoodLocale getLocale() {
        return elementConfig.getLocale(food);
    }
    
    @XmlAttribute
    public Integer getId() {
        return food.getId();
    }

    @XmlElement
    public String getName() {
        FoodLocale l = getLocale();
        return l == null ? null : l.getName();
    }

    @XmlElement
    public String getDescription() {
        FoodLocale l = getLocale();
        return l == null ? null : l.getDescription();
    }

    @XmlElement
    public String getPhotoUrl() {
        return food.getPhotoUrl();
    }

    @XmlElement
    public List<PriceElement> getPrices() {
        return Converters.convertList(food.getPrices(), new PriceElementConverter(elementConfig));
    }

    @XmlElement
    public List<ExtraElement> getExtras() {
        if (!elementConfig.withNestedObjects()) {
            return null;
        }
        return Converters.convertList(food.getExtras(), new ExtraElementConverter(elementConfig));
    }
    
}

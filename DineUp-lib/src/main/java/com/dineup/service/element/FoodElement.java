package com.dineup.service.element;

import com.dineup.service.ElementContext;
import com.dineup.dom.Food;
import com.dineup.dom.FoodLocale;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.converter.ExtraElementConverter;
import com.dineup.service.element.converter.PriceElementConverter;
import com.dineup.util.Converters;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "food")
public class FoodElement {
    
    private ElementContext elementContext;
    private ElementConfig elementConfig;
    private Food food;
    
    public FoodElement() {
    }

    public FoodElement(ElementContext elementContext, ElementConfig elementConfig, Food food) {
        this.elementContext = elementContext;
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
        return Converters.convertList(food.getPrices(), new PriceElementConverter(elementContext, elementConfig));
    }

    @XmlElement
    public List<ExtraElement> getExtras() {
        if (!elementConfig.withNestedObjects()) {
            return null;
        }
        return Converters.convertList(food.getExtras(), new ExtraElementConverter(elementContext, elementConfig));
    }
    
}

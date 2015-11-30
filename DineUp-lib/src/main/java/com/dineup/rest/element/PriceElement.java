package com.dineup.rest.element;

import com.dineup.dom.Price;
import com.dineup.rest.ElementConfig;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "price")
public class PriceElement {
    
    private ElementConfig elementConfig;
    private Price price;
    
    public PriceElement() {
    }

    public PriceElement(ElementConfig elementConfig, Price price) {
        this.elementConfig = elementConfig;
        this.price = price;
    }

    @XmlElement
    public BigDecimal getAmount() {
        return price.getAmount();
    }

    @XmlElement
    public String getCurrency() {
        return price.getCurrency();
    }
    
}

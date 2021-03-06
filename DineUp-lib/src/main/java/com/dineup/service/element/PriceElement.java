package com.dineup.service.element;

import com.dineup.service.ElementContext;
import com.dineup.dom.Price;
import com.dineup.service.ElementConfig;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "price")
public class PriceElement {
    
    private ElementContext elementContext;
    private ElementConfig elementConfig;
    private Price price;
    
    public PriceElement() {
    }

    public PriceElement(ElementContext elementContext, ElementConfig elementConfig, Price price) {
        this.elementContext = elementContext;
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

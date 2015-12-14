package com.dineup.api.service.element;

import com.dineup.api.dom.Price;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;

public class PriceElement implements Price, Serializable {

    @XmlElement
    public String currency;
    
    @XmlElement
    public BigDecimal amount;
    
    public PriceElement() {
    }
    
    @Override
    public String toString() {
        return String.format("Price(%s %s)", currency, amount);
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }
    
}

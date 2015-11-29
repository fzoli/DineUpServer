package com.dineup.rest.element;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "price")
public class PriceElement {
    
    @XmlElement
    public BigDecimal amount;
    
    @XmlElement
    public String currency;

    public PriceElement() {
    }
    
}

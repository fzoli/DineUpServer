package com.dineup.api.service.impl.v1_0.element;

import com.dineup.api.dom.Price;
import java.io.Serializable;
import java.math.BigDecimal;

public class PriceElement implements Price, Serializable {

    public String currency;
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

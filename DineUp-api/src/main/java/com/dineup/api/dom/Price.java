package com.dineup.api.dom;

import java.math.BigDecimal;

public interface Price {
    public BigDecimal getAmount();
    public String getCurrency();
}

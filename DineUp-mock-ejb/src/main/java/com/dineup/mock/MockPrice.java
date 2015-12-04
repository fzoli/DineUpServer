package com.dineup.mock;

import com.dineup.ejb.db.MockDatas;
import com.dineup.dom.Price;
import java.math.BigDecimal;

public class MockPrice implements Price, MockDatas {

    @Override
    public BigDecimal getAmount() {
        return new BigDecimal(164.3);
    }

    @Override
    public String getCurrency() {
        return CURRENCY;
    }
    
}

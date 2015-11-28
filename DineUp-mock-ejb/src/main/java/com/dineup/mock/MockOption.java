/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dineup.mock;

import com.dineup.ejb.MockDatas;
import com.dineup.dom.Option;
import com.dineup.dom.OptionLocale;
import com.dineup.dom.Price;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author zoli
 */
public class MockOption implements Option, MockDatas {
    private final int id;
    
    public MockOption(int id) {
        this.id = id;
    }

    @Override
    public List<Price> getPrices() {
        return (List) Collections.singletonList(new MockPrice());
    }

    @Override
    public List<OptionLocale> getLocales() {
        return (List) Collections.singletonList(new MockOptionLocale(id));
    }
}

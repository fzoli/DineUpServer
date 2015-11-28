/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dineup.mock;

import com.dineup.ejb.MockDatas;
import com.dineup.dom.Extra;
import com.dineup.dom.ExtraLocale;
import com.dineup.dom.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author zoli
 */
public class MockExtra implements Extra, MockDatas {

    private final int id;
    
    MockExtra(int id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return EXTRA_TYPES[id % EXTRA_TYPES.length];
    }

    @Override
    public List<ExtraLocale> getLocales() {
        return (List) Collections.singletonList(new MockExtraLocale(id));
    }

    @Override
    public List<Option> getOptions() {
        List<Option> list = new ArrayList<>(NUMBER_OF_OPTIONS);
        for (int i = 0; i < NUMBER_OF_OPTIONS; i++) {
            list.add(new MockOption(i));
        }
        return Collections.unmodifiableList(list);
    }
    
}

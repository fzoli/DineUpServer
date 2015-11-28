/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dineup.mock;

import com.dineup.ejb.MockDatas;
import com.dineup.dom.OptionLocale;

/**
 *
 * @author zoli
 */
public class MockOptionLocale implements OptionLocale, MockDatas {
    private final int id;
    
    public MockOptionLocale(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return "Option" + id;
    }

    @Override
    public String getLanguageCode() {
        return "en";
    }
}

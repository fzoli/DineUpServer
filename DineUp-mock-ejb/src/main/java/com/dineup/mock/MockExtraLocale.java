/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dineup.mock;

import com.dineup.ejb.MockDatas;
import com.dineup.dom.ExtraLocale;

/**
 *
 * @author zoli
 */
public class MockExtraLocale implements ExtraLocale, MockDatas {

    private final int id;
    
    public MockExtraLocale(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return "eXTRA" + id;
    }

    @Override
    public String getLanguageCode() {
        return "en";
    }
    
}

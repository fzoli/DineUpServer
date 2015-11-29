package com.dineup.dom;

import java.util.List;

public interface Option extends LocalizedObject<OptionLocale> {
    public List<Price> getPrices();
}

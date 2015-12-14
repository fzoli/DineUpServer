package com.dineup.api.dom;

import java.util.List;

public interface Option {
    public int getId();
    public String getLanguageCode();
    public String getName();
    public List<Price> getPrices();
}

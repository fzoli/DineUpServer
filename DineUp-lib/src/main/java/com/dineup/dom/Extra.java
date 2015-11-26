package com.dineup.dom;

import java.util.List;

public interface Extra {
    public String getType();
    public List<ExtraLocale> getLocales();
    public List<Option> getOptions();
}

package com.dineup.dom;

import java.util.List;

public interface Extra extends LocalizedObject<ExtraLocale> {
    public Integer getId();
    public String getType();
    public List<Option> getOptions();
}

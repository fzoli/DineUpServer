package com.dineup.dom;

import java.util.List;

public interface LocalizedObject<LocaleType extends Locale> {
    public List<LocaleType> getLocales();
}

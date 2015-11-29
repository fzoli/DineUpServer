package com.dineup.rest.element.converter;

import com.dineup.dom.Locale;
import com.dineup.dom.Locales;
import com.dineup.dom.LocalizedObject;
import com.dineup.rest.ElementConfig;
import com.dineup.util.Converter;

abstract class BaseLocalizedElementConverter<In extends LocalizedObject<InLocale>, InLocale extends Locale, Out> implements Converter<In, Out> {

    protected final ElementConfig elementConfig;
    
    protected BaseLocalizedElementConverter(ElementConfig elementConfig) {
        this.elementConfig = elementConfig;
    }
    
    protected InLocale getLocale(In obj) {
        InLocale l = Locales.getLocale(obj, elementConfig.getLanguageCode());
        if (l == null) l = Locales.getLocale(obj, elementConfig.getDefaultLanguageCode());
        return l;
    }
    
}

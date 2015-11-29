package com.dineup.rest.element.converter;

import com.dineup.dom.Option;
import com.dineup.dom.OptionLocale;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.OptionElement;
import com.dineup.util.Converters;

public class OptionElementConverter extends BaseLocalizedElementConverter<Option, OptionLocale, OptionElement> {

    public OptionElementConverter(ElementConfig elementConfig) {
        super(elementConfig);
    }

    @Override
    public OptionElement convert(Option o) {
        if (o == null) {
            return null;
        }
        OptionElement e = new OptionElement();
        OptionLocale l = getLocale(o);
        if (l != null) {
            e.name = l.getName();
        }
        e.prices = Converters.convertList(o.getPrices(), new PriceElementConverter(elementConfig));
        return e;
    }
    
}

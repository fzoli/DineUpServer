package com.dineup.rest.element.converter;

import com.dineup.dom.Extra;
import com.dineup.dom.ExtraLocale;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.ExtraElement;
import com.dineup.util.Converters;

public class ExtraElementConverter extends BaseLocalizedElementConverter<Extra, ExtraLocale, ExtraElement> {

    public ExtraElementConverter(ElementConfig elementConfig) {
        super(elementConfig);
    }

    @Override
    public ExtraElement convert(Extra o) {
        if (o == null) {
            return null;
        }
        ExtraElement e = new ExtraElement();
        ExtraLocale l = getLocale(o);
        if (l != null) {
            e.name = l.getName();
        }
        if (elementConfig.withNestedObjects()) {
            e.options = Converters.convertList(o.getOptions(), new OptionElementConverter(elementConfig));
        }
        e.type = o.getType();
        return e;
    }
    
}

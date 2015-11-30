package com.dineup.rest.element;

import com.dineup.dom.Option;
import com.dineup.dom.OptionLocale;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.converter.PriceElementConverter;
import com.dineup.util.Converters;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "option")
public class OptionElement {

    private ElementConfig elementConfig;
    private Option option;
    
    public OptionElement() {
    }

    public OptionElement(ElementConfig elementConfig, Option option) {
        this.elementConfig = elementConfig;
        this.option = option;
    }

    private OptionLocale getLocale() {
        return elementConfig.getLocale(option);
    }
    
    @XmlElement
    public Integer getId() {
        return option.getId();
    }
    
    @XmlElement
    public String getName() {
        OptionLocale l = getLocale();
        return l == null ? null : l.getName();
    }

    @XmlElement
    public List<PriceElement> getPrices() {
        return Converters.convertList(option.getPrices(), new PriceElementConverter(elementConfig));
    }
    
}

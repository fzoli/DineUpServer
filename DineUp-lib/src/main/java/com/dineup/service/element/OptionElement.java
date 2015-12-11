package com.dineup.service.element;

import com.dineup.service.ElementContext;
import com.dineup.dom.Option;
import com.dineup.dom.OptionLocale;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.converter.PriceElementConverter;
import com.dineup.util.Converters;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "option")
public class OptionElement {

    private ElementContext elementContext;
    private ElementConfig elementConfig;
    private Option option;
    
    public OptionElement() {
    }

    public OptionElement(ElementContext elementContext, ElementConfig elementConfig, Option option) {
        this.elementContext = elementContext;
        this.elementConfig = elementConfig;
        this.option = option;
    }

    private OptionLocale getLocale() {
        return elementConfig.getLocale(option);
    }
    
    @XmlAttribute
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
        return Converters.convertList(option.getPrices(), new PriceElementConverter(elementContext, elementConfig));
    }
    
}

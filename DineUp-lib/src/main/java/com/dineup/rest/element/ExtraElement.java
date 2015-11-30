package com.dineup.rest.element;

import com.dineup.dom.Extra;
import com.dineup.dom.ExtraLocale;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.converter.OptionElementConverter;
import com.dineup.util.Converters;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "extra")
public class ExtraElement {

    private ElementConfig elementConfig;
    private Extra extra;
    
    public ExtraElement() {
    }

    public ExtraElement(ElementConfig elementConfig, Extra extra) {
        this.elementConfig = elementConfig;
        this.extra = extra;
    }

    private ExtraLocale getLocale() {
        return elementConfig.getLocale(extra);
    }
    
    @XmlElement
    public Integer getId() {
        return extra.getId();
    }
    
    @XmlElement
    public String getName() {
        ExtraLocale l = getLocale();
        return l == null ? null : l.getName();
    }

    @XmlElement
    public String getType() {
        return extra.getType();
    }

    @XmlElement
    public List<OptionElement> getOptions() {
        if (!elementConfig.withNestedObjects()) {
            return null;
        }
        return Converters.convertList(extra.getOptions(), new OptionElementConverter(elementConfig));
    }
    
}

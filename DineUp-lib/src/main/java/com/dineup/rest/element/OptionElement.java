package com.dineup.rest.element;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "option")
public class OptionElement {

    @XmlElement
    public String name;
    
    @XmlElement
    public List<PriceElement> prices;
    
    public OptionElement() {
    }
    
}

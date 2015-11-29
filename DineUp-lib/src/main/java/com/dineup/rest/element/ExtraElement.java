package com.dineup.rest.element;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "extra")
public class ExtraElement {

    @XmlElement
    public String name;
    
    @XmlElement
    public String type;
    
    @XmlElement
    public List<OptionElement> options;
    
    public ExtraElement() {
    }
    
}

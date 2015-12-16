package com.dineup.api.service.jaxrs.v1_0.element;

import com.dineup.api.dom.Extra;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class ExtraElement implements Extra, Serializable {

    @XmlAttribute
    public int id;
    
    @XmlElement
    public String languageCode;
    
    @XmlElement
    public String type;
    
    @XmlElement
    public String name;
    
    public ExtraElement() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return String.format("Extra(id=%s)", id);
    }
    
}

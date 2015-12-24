package com.dineup.api.service.jaxrs.v1_0.element;

import com.dineup.api.dom.Message;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "message")
public class MessageElement implements Message, Serializable {
    
    @XmlElement
    public String languageCode;
    
    @XmlElement
    public String text;

    public MessageElement() {
    }
    
    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
    }
    
    @Override
    public String toString() {
        return String.format("Message(languageCode=%s)", languageCode);
    }

}

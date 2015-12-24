package com.dineup.service.element;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.dineup.dom.Message;

@XmlRootElement(name = "message")
public class MessageElement {

    private Message message;
    
    public MessageElement(Message message) {
        this.message = message;
    }

    public MessageElement() {
    }
    
    @XmlElement
    public String getText() {
        return message.getText();
    }
    
    @XmlElement
    public String getLanguageCode() {
        return message.getLanguageCode();
    }
    
}

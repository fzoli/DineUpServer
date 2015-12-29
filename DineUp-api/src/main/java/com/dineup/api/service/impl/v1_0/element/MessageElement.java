package com.dineup.api.service.impl.v1_0.element;

import com.dineup.api.dom.Message;
import java.io.Serializable;

public class MessageElement implements Message, Serializable {
    
    public String languageCode;
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

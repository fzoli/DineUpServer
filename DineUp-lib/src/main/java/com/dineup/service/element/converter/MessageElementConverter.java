package com.dineup.service.element.converter;

import com.dineup.dom.Message;
import com.dineup.service.ElementContext;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.MessageElement;

public class MessageElementConverter extends BaseElementConverter<Message, MessageElement> {

    public MessageElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    public MessageElement safeConvert(Message message) {
        return new MessageElement(message);
    }
    
}

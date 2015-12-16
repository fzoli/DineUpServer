package com.dineup.api.service.httpclient.v1_0.element;

import com.dineup.api.dom.Extra;
import java.io.Serializable;

public class ExtraElement implements Extra, Serializable {

    public int id;
    public String languageCode;
    public String type;
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

package com.dineup.api.service.element;

import com.dineup.api.dom.Category;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
public class CategoryElement implements Category, Serializable {
    
    @XmlAttribute
    public int id;
    
    @XmlElement
    public String languageCode;

    @XmlElement
    public String name;
    
    @XmlElement
    public String photoUrl;
    
    public CategoryElement() {
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
    public String getName() {
        return name;
    }

    @Override
    public String getPhotoUrl() {
        return photoUrl;
    }
    
    @Override
    public String toString() {
        return String.format("Category(id=%s)", id);
    }
    
}

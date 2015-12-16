package com.dineup.api.service.httpclient.v1_0.element;

import com.dineup.api.dom.Category;
import java.io.Serializable;

public class CategoryElement implements Category, Serializable {
    
    public int id;
    public String languageCode;
    public String name;
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

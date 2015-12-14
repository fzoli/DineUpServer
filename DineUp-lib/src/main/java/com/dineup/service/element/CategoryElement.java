package com.dineup.service.element;

import com.dineup.dom.Category;
import com.dineup.dom.CategoryLocale;
import com.dineup.dom.Locale;
import com.dineup.service.ElementContext;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.converter.FoodElementConverter;
import com.dineup.util.Converters;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
public class CategoryElement {

    private ElementContext elementContext;
    private ElementConfig elementConfig;
    private Category category;
    
    public CategoryElement() {
    }

    public CategoryElement(ElementContext elementContext, ElementConfig elementConfig, Category category) {
        this.elementContext = elementContext;
        this.elementConfig = elementConfig;
        this.category = category;
    }

    private CategoryLocale getLocale() {
        return elementConfig.getLocale(category);
    }
    
    @XmlAttribute
    public Integer getId() {
        return category.getId();
    }

    @XmlElement
    public String getLanguageCode() {
        Locale l = getLocale();
        return l == null ? null : l.getLanguageCode();
    }
    
    @XmlElement
    public String getName() {
        CategoryLocale l = getLocale();
        return l == null ? null : l.getName();
    }

    @XmlElement
    public String getPhotoUrl() {
        return category.getPhotoUrl();
    }

    @XmlElement
    public List<FoodElement> getFoods() {
        if (!elementConfig.withNestedObjects()) {
            return null;
        }
        return Converters.convertList(category.getFoods(), new FoodElementConverter(elementContext, elementConfig));
    }
    
}

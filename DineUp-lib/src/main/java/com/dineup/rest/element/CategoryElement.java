package com.dineup.rest.element;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
public class CategoryElement {

    @XmlElement
    public Integer id;
    
    @XmlElement
    public String name;
    
    @XmlElement
    public String photoUrl;
    
    @XmlElement
    public List<FoodElement> foods;
    
    public CategoryElement() {
    }
    
}

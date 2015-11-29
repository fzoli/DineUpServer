package com.dineup.rest.element;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "food")
public class FoodElement {

    @XmlElement
    public Integer id;
    
    @XmlElement
    public String name;
    
    @XmlElement
    public String description;
    
    @XmlElement
    public String photoUrl;
    
    @XmlElement
    public List<PriceElement> prices;
    
    @XmlElement
    public List<ExtraElement> extras;
    
    public FoodElement() {
    }
    
}

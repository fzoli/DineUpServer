package com.dineup.rest.element;

import com.dineup.dom.Profile;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.converter.PersonElementConverter;
import com.dineup.util.Converters;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profile")
public class ProfileElement {
    
    private ElementConfig elementConfig;
    private Profile profile;
    
    public ProfileElement(ElementConfig elementConfig, Profile profile) {
        this.elementConfig = elementConfig;
        this.profile = profile;
    }

    public ProfileElement() {
    }
    
    @XmlElement
    public Date getLastSync() {
        return profile.getLastSync();
    }
    
    @XmlElement
    public String getType() {
        return profile.getType().name();
    }
    
    @XmlElement
    public String getPhotoUrl() {
        return profile.getPhotoUrl();
    }
    
    @XmlElement
    public PersonElement getPerson() {
        return Converters.convert(profile.getPerson(), new PersonElementConverter(elementConfig));
    }
    
}

package com.dineup.service.element;

import com.dineup.service.ElementContext;
import com.dineup.dom.Profile;
import com.dineup.ejb.profile.ProfileDescriptor;
import com.dineup.ejb.profile.ProfileManager;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.converter.PersonElementConverter;
import com.dineup.util.Converters;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profile")
public class ProfileElement {
    
    private ElementContext elementContext;
    private ElementConfig elementConfig;
    private Profile profile;
    
    public ProfileElement(ElementContext elementContext, ElementConfig elementConfig, Profile profile) {
        this.elementContext = elementContext;
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
    public PersonElement getPerson() {
        return Converters.convert(profile.getPerson(), new PersonElementConverter(elementContext, elementConfig));
    }
    
    @XmlElement
    public String getPhotoUrl() {
        if (elementContext.getProfileManagerFactory() == null) {
            return null;
        }
        ProfileDescriptor descriptor = elementConfig.createProfileDescriptor(profile.getType());
        ProfileManager profileManager = elementContext.getProfileManagerFactory().createManager(descriptor);
        if (profileManager != null) {
            return profileManager.getProfilePhotoUrl(profile.getUserId());
        }
        return null;
    }
    
}

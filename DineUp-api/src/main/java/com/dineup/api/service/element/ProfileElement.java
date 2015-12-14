package com.dineup.api.service.element;

import com.dineup.api.dom.Person;
import com.dineup.api.dom.Profile;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profile")
public class ProfileElement implements Profile {
    
    @XmlElement
    public Date lastSync;
    
    @XmlElement
    public PersonElement person;
    
    @XmlElement
    public String photoUrl;
    
    @XmlElement
    public Profile.Type type;

    public ProfileElement() {
    }

    @Override
    public Date getLastSync() {
        return lastSync;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public String getPhotoUrl() {
        return photoUrl;
    }

    @Override
    public Profile.Type getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return String.format("Profile(type=%s)", type);
    }
    
}

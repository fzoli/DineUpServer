package com.dineup.api.service.impl.v1_0.element;

import com.dineup.api.dom.Person;
import com.dineup.api.dom.Profile;
import java.io.Serializable;
import java.util.Date;

public class ProfileElement implements Profile, Serializable {
    
    public Date lastSync;
    public PersonElement person;
    public String photoUrl;
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

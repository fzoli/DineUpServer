package com.dineup.dom;

import com.dineup.ejb.profile.ProfileDescriptor;
import com.dineup.ejb.profile.ProfileManager;
import java.util.Date;

public interface Profile {
    
    public enum Type {
        FACEBOOK, GOOGLE_PLUS
    }
    
    public Date getLastSync();
    public String getUserId();
    public Type getType();
    public Person getPerson();
    public ProfileManager createProfileManager(ProfileDescriptor descriptor);
}

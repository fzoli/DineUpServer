package com.dineup.ejb.profile;

import javax.ejb.Local;

@Local
public interface ProfileManagerFactory {
    public ProfileManager createManager(ProfileDescriptor profileDescriptor);
}

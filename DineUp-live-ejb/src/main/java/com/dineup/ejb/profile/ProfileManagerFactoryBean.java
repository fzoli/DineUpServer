package com.dineup.ejb.profile;

import javax.ejb.Singleton;

@Singleton
public class ProfileManagerFactoryBean implements ProfileManagerFactory {
    
    @Override
    public ProfileManager createManager(ProfileDescriptor profileDescriptor) {
        if (profileDescriptor != null) {
            ProfileManager manager = _createManager(profileDescriptor);
            if (manager != null && manager.getProfileType() != profileDescriptor.getProfileType()) {
                throw new IllegalStateException("Profile missmatch. Fix code!");
            }
            return manager;
        }
        return null;
    }
    
    private ProfileManager _createManager(ProfileDescriptor profileDescriptor) {
        switch (profileDescriptor.getProfileType()) {
            case FACEBOOK:
                return new FacebookProfileManager(profileDescriptor.getAccessToken());
            case GOOGLE_PLUS:
                return new GoogleProfileManager(profileDescriptor.getAccessToken());
        }
        return null;
    }
    
}

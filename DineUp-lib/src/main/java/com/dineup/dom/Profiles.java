package com.dineup.dom;

import com.dineup.ejb.profile.ProfileDescriptor;
import com.dineup.ejb.profile.ProfileManager;
import com.dineup.ejb.profile.ProfileManagerFactory;

public class Profiles {

    public static ProfileManager createProfileManager(Profile profile, ProfileManagerFactory factory, ProfileDescriptor descriptor) {
        if (profile == null || factory == null) {
            return null;
        }
        if (descriptor == null) {
            descriptor = ProfileDescriptor.newBuilder()
                    .profileType(profile.getType())
                    .build();
        }
        return factory.createManager(descriptor);
    }
    
    private Profiles() {
    }
    
}

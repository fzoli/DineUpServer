package com.dineup.rest;

import com.dineup.ejb.profile.ProfileManagerFactory;

/**
 * Application scope dependencies used by Elements.
 */
public class ElementContext {
    
    private final ProfileManagerFactory profileManagerFactory;

    private ElementContext(Builder builder) {
        profileManagerFactory = builder.profileManagerFactory;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public ProfileManagerFactory getProfileManagerFactory() {
        return profileManagerFactory;
    }

    public static final class Builder {
        private ProfileManagerFactory profileManagerFactory;

        private Builder() {
        }

        public Builder profileManagerFactory(ProfileManagerFactory profileManagerFactory) {
            this.profileManagerFactory = profileManagerFactory;
            return this;
        }

        public ElementContext build() {
            return new ElementContext(this);
        }
    }
}

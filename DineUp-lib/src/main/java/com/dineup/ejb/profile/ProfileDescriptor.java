package com.dineup.ejb.profile;

import com.dineup.dom.Profile;

public class ProfileDescriptor {

    private final Profile.Type profileType;
    private final String accessToken;

    private ProfileDescriptor(Builder builder) {
        profileType = builder.profileType;
        accessToken = builder.accessToken;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Profile.Type getProfileType() {
        return profileType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public static final class Builder {

        private Profile.Type profileType;
        private String accessToken;

        private Builder() {
        }

        public Builder profileType(Profile.Type profileType) {
            this.profileType = profileType;
            return this;
        }

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public ProfileDescriptor build() {
            return new ProfileDescriptor(this);
        }
    }

}

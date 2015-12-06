package com.dineup.ejb.profile;

import com.dineup.dom.Profile;
import javax.annotation.Nullable;

final class GoogleProfileManager implements ProfileManager {

    private final String accessToken;
    
    public GoogleProfileManager(@Nullable String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Profile.Type getProfileType() {
        return Profile.Type.GOOGLE_PLUS;
    }
    
    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getProfilePhotoUrl(String userId) {
        return "TODO";
    }
    
    @Override
    public ProfileResult getProfile() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

package com.dineup.ejb.profile;

import com.dineup.dom.Profile;
import javax.annotation.Nullable;

final class FacebookProfileManager implements ProfileManager {

    private final String accessToken;
    
    public FacebookProfileManager(@Nullable String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Profile.Type getProfileType() {
        return Profile.Type.FACEBOOK;
    }
    
    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getProfilePhotoUrl(String userId) {
        if (userId == null) {
            return null;
        }
        return String.format("https://graph.facebook.com/%s/picture?type=large%s", userId, accessToken == null ? "" : "&access_token=" + accessToken);
    }
    
    @Override
    public String resolveProfilePhotoUrl(String userId) throws Exception {
        throw new UnsupportedOperationException("Not used.");
    }
    
    @Override
    public ProfileResult getProfile() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // TODO
    }

}

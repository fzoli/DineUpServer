package com.dineup.ejb.profile;

import com.dineup.dom.Profile;

public interface ProfileManager {
    public String getAccessToken();
    public ProfileResult getProfile() throws Exception;
    public String resolveProfilePhotoUrl(String userId) throws Exception;
    public String getProfilePhotoUrl(String userId);
    public Profile.Type getProfileType();
}

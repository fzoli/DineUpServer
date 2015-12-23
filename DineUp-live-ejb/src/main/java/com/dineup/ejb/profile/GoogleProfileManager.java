package com.dineup.ejb.profile;

import com.dineup.BuildConfig;
import com.dineup.dom.Profile;
import com.dineup.service.rest.ProfileKeys;
import com.dineup.service.rest.RequestPath;
import com.dineup.util.Strings;

final class GoogleProfileManager implements ProfileManager {

    private final String accessToken;
    
    public GoogleProfileManager(String accessToken) {
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
        if (userId == null) {
            return null;
        }
        return Strings.concat("/", "/", BuildConfig.WEB_CONTEXT_ROOT, BuildConfig.WEB_REST_ROOT, RequestPath.PATH_GOOGLE_PROFILE_PHOTO + "?" + ProfileKeys.USER_ID + "=" + userId);
    }

    @Override
    public String resolveProfilePhotoUrl(String userId) throws Exception {
        String requestUrl = String.format("https://www.googleapis.com/plus/v1/people/%s?fields=image&key=%s", userId, BuildConfig.GOOGLE_API_KEY);
        // TODO:
        // Parse response: {"image": {"url": "https://lh3.googleusercontent.com/-OkM...AANA/ltpH4BFZ2as/photo.jpg?sz=50"}}
        // return "https://lh3.googleusercontent.com/-OkM...AANA/ltpH4BFZ2as/photo.jpg?sz=50";
        throw new UnsupportedOperationException("Not supported yet."); // TODO
    }
    
    @Override
    public ProfileResult getProfile() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // TODO
    }

}

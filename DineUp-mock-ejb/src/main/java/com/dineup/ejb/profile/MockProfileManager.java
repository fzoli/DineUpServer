package com.dineup.ejb.profile;

import com.dineup.dom.Person;
import com.dineup.dom.Profile;
import java.util.Date;

final class MockProfileManager implements ProfileManager {

    private final String accessToken;
    private final Profile.Type type;
    
    public MockProfileManager(Profile.Type type, String accessToken) {
        this.accessToken = accessToken;
        this.type = type;
    }

    @Override
    public Profile.Type getProfileType() {
        return type;
    }
    
    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public ProfileResult getProfile() throws Exception {
        return ProfileResult.newBuilder()
                .birthDate(new Date())
                .firstName("First")
                .lastName("Last")
                .middleName("Middle")
                .gender(Person.Gender.MALE)
                .type(type)
                .userId("5")
                .build();
    }

    @Override
    public String getProfilePhotoUrl(String userId) {
        return "https://placeholdit.imgix.net/~text?txtsize=33&txt=Profile%20image&w=150&h=150";
    }

    @Override
    public String resolveProfilePhotoUrl(String userId) throws Exception {
        throw new UnsupportedOperationException("Not used.");
    }
    
}

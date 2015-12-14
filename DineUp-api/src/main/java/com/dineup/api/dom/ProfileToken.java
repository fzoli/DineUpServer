package com.dineup.api.dom;

import com.dineup.util.Strings;

public final class ProfileToken {

    private final String accessToken;
    private final Profile.Type type;

    public String getAccessToken() {
        return accessToken;
    }

    public Profile.Type getType() {
        return type;
    }

    public ProfileToken(String accessToken, Profile.Type type) {
        if (Strings.isEmptyText(accessToken)) {
            throw new IllegalArgumentException("Invalid accessToken");
        }
        if (type == null) {
            throw new IllegalArgumentException("Invalid type");
        }
        this.accessToken = accessToken;
        this.type = type;
    }

}

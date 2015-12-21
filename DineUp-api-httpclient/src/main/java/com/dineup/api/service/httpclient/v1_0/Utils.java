package com.dineup.api.service.httpclient.v1_0;

import com.dineup.api.ApiConfig;
import com.dineup.api.dom.ProfileToken;
import com.dineup.api.service.httpclient.v1_0.element.ProfileElement;
import com.dineup.service.rest.ElementConfigKeys;
import com.dineup.util.Strings;
import java.util.Map;

public class Utils {

    public static void putProfileParameters(Map<String, Object> parameters, ProfileToken profileToken) {
        if (profileToken == null) {
            return;
        }
        switch (profileToken.getType()) {
            case FACEBOOK:
                parameters.put(ElementConfigKeys.FACEBOOK_ACCESS_TOKEN, profileToken.getAccessToken());
                break;
            case GOOGLE_PLUS:
                parameters.put(ElementConfigKeys.GOOGLE_ACCESS_TOKEN, profileToken.getAccessToken());
                break;
        }
    }
    
    public static void completeProfileElement(ProfileElement profile, ApiConfig targetConfig) {
        if (profile == null) {
            return;
        }
        String photoUrl = profile.photoUrl;
        if (photoUrl != null && photoUrl.startsWith("/")) {
            profile.photoUrl = Strings.concat("/", targetConfig.getServerUrl(), photoUrl);
        }
    }
    
    private Utils() {
    }
    
}

package com.dineup.api;

import com.dineup.util.Strings;
import org.apache.commons.validator.routines.UrlValidator;

public class TargetConfig {

    private final String serverUrl;
    private final String webAppRoot;
    private final String restRoot;
    private final String languageCode;

    private static final String[] validUrlSchemes = {"http", "https"};
    private static final UrlValidator urlValidator = new UrlValidator(validUrlSchemes, UrlValidator.ALLOW_LOCAL_URLS);
    
    private TargetConfig(Builder builder) {
        if (!urlValidator.isValid(builder.serverUrl)) {
            throw new IllegalArgumentException("Invalid serverUrl");
        }
        if (builder.languageCode != null && builder.languageCode.length() != 2) {
            throw new IllegalArgumentException("Invalid languageCode");
        }
        serverUrl = builder.serverUrl;
        webAppRoot = builder.webAppRoot;
        restRoot = builder.restRoot;
        languageCode = builder.languageCode;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public String getWebAppRoot() {
        return webAppRoot;
    }

    public String getRestRoot() {
        return restRoot;
    }

    public String getLanguageCode() {
        return languageCode;
    }
    
    public String getTarget() {
        return Strings.concat("/", getServerUrl(), getWebAppRoot(), getRestRoot());
    }
    
    public static final class Builder {
        
        private String serverUrl;
        private String webAppRoot;
        private String restRoot;
        private String languageCode;

        private Builder() {
        }

        public Builder languageCode(String languageCode) {
            this.languageCode = languageCode;
            return this;
        }
        
        public Builder serverUrl(String serverUrl) {
            this.serverUrl = serverUrl;
            return this;
        }
        
        public Builder webAppRoot(String webAppRoot) {
            this.webAppRoot = webAppRoot;
            return this;
        }

        public Builder restRoot(String restRoot) {
            this.restRoot = restRoot;
            return this;
        }

        public com.dineup.api.TargetConfig build() {
            return new TargetConfig(this);
        }
        
    }

}

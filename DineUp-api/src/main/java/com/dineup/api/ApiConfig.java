package com.dineup.api;

import com.dineup.api.service.util.UrlValidator;
import com.dineup.util.Strings;

public class ApiConfig {

    private final String serverUrl;
    private final String webAppRoot;
    private final String restRoot;
    private final String languageCode;

    private static final String[] VALID_URL_SCHEMES = {"http", "https"};
    private static final UrlValidator URL_VALIDATOR = new UrlValidator(VALID_URL_SCHEMES, UrlValidator.ALLOW_LOCAL_URLS);
    
    private ApiConfig(Builder builder) {
        if (!URL_VALIDATOR.isValid(builder.serverUrl)) {
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

        public com.dineup.api.ApiConfig build() {
            return new ApiConfig(this);
        }
        
    }

}

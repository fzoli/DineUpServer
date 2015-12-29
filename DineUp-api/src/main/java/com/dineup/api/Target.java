package com.dineup.api;

import com.dineup.api.service.util.UrlValidator;
import com.dineup.util.Strings;

public class Target {
    
    private final String serverUrl;
    private final String webAppRoot;
    private final String restRoot;
    
    private Target(Builder builder) {
        serverUrl = builder.serverUrl;
        webAppRoot = builder.webAppRoot;
        restRoot = builder.restRoot;
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
    
    @Override
    public String toString() {
        return Strings.concat("/", getServerUrl(), getWebAppRoot(), getRestRoot());
    }
    
    public static final class Builder {
        
        private String serverUrl;
        private String webAppRoot;
        private String restRoot;

        private Builder() {
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
        
        public Target build() {
            validate();
            return new Target(this);
        }
        
        private static final String[] VALID_URL_SCHEMES = {"http", "https"};
        private static final UrlValidator URL_VALIDATOR = new UrlValidator(VALID_URL_SCHEMES, UrlValidator.ALLOW_LOCAL_URLS);
        
        private void validate() {
            if (!URL_VALIDATOR.isValid(serverUrl)) {
                throw new IllegalArgumentException("Invalid serverUrl");
            }
        }
        
    }
    
}

package com.dineup.api;

import java.security.KeyStore;
import javax.net.ssl.HostnameVerifier;

public class SecurityConfig {

    private final HostnameVerifier hostnameVerifier;
    private final KeyStore keyStore;

    public HostnameVerifier getHostnameVerifier() {
        return hostnameVerifier;
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    private SecurityConfig(Builder builder) {
        hostnameVerifier = builder.hostnameVerifier;
        keyStore = builder.keyStore;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        
        private HostnameVerifier hostnameVerifier;
        private KeyStore keyStore;

        private Builder() {
        }

        public Builder hostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.hostnameVerifier = hostnameVerifier;
            return this;
        }

        public Builder keyStore(KeyStore keyStore) {
            this.keyStore = keyStore;
            return this;
        }

        public com.dineup.api.SecurityConfig build() {
            return new SecurityConfig(this);
        }
        
    }

}

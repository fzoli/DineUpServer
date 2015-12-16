package com.dineup.api;

import java.security.KeyStore;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

public class ClientConfig {

    private final HostnameVerifier hostnameVerifier;
    private final KeyStore keyStore;
    private final String keyStorePassword;
    private final SSLContext sslContext;

    public HostnameVerifier getHostnameVerifier() {
        return hostnameVerifier;
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    public SSLContext getSslContext() {
        return sslContext;
    }

    private ClientConfig(Builder builder) {
        sslContext = builder.sslContext;
        hostnameVerifier = builder.hostnameVerifier;
        keyStore = builder.keyStore;
        keyStorePassword = builder.keyStorePassword;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        
        private SSLContext sslContext;
        private HostnameVerifier hostnameVerifier;
        private KeyStore keyStore;
        private String keyStorePassword;

        private Builder() {
        }

        public Builder sslContext(SSLContext sslContext) {
            this.sslContext = sslContext;
            return this;
        }

        public Builder hostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.hostnameVerifier = hostnameVerifier;
            return this;
        }

        public Builder keyStore(KeyStore keyStore, String password) {
            this.keyStore = keyStore;
            this.keyStorePassword = password;
            return this;
        }

        public com.dineup.api.ClientConfig build() {
            return new ClientConfig(this);
        }
        
    }

}

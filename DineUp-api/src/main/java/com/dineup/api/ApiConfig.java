package com.dineup.api;

import com.dineup.api.service.impl.DineUpNoCacheManager;

public class ApiConfig {

    private final SecurityConfig securityConfig;
    private final DineUpCacheManager cacheManager;
    private final Target target;
    private final String languageCode;

    private ApiConfig(Builder builder) {
        securityConfig = builder.securityConfig;
        cacheManager = builder.cacheManager;
        target = builder.target;
        languageCode = builder.languageCode;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public SecurityConfig getSecurityConfig() {
        return securityConfig;
    }

    public DineUpCacheManager getCacheManager() {
        return cacheManager;
    }
    
    public Target getTarget() {
        return target;
    }

    public String getLanguageCode() {
        return languageCode;
    }
    
    public static final class Builder {
        
        private SecurityConfig securityConfig;
        private DineUpCacheManager cacheManager;
        private Target target;
        private String languageCode;

        private Builder() {
        }
        
        public Builder securityConfig(SecurityConfig securityConfig) {
            this.securityConfig = securityConfig;
            return this;
        }
        
        public Builder cacheManager(DineUpCacheManager cacheManager) {
            this.cacheManager = cacheManager;
            return this;
        }
        
        public Builder target(Target target) {
            this.target = target;
            return this;
        }
        
        public Builder languageCode(String languageCode) {
            this.languageCode = languageCode;
            return this;
        }
        
        public com.dineup.api.ApiConfig build() {
            complete();
            validate();
            return new ApiConfig(this);
        }
    
        private static final int LANGUAGE_CODE_LENGTH = 2;
        
        private void complete() {
            if (securityConfig == null) {
                securityConfig = SecurityConfig.newBuilder().build();
            }
            if (cacheManager == null) {
                cacheManager = new DineUpNoCacheManager();
            }
        }
        
        private void validate() {
            if (target == null) {
                throw new IllegalArgumentException("Target is not specified");
            }
            if (languageCode != null && languageCode.length() != LANGUAGE_CODE_LENGTH) {
                throw new IllegalArgumentException("Invalid languageCode");
            }
        }
        
    }

}

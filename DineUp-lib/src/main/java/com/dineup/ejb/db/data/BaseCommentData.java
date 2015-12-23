package com.dineup.ejb.db.data;

import com.dineup.ejb.profile.ProfileResult;

import java.util.Date;

public class BaseCommentData {
    
    private final ProfileResult profileResult;
    private final boolean publicProfile;
    private final double rating;
    private final String message;
    private final String languageCode;
    private final Date time;

    protected BaseCommentData(BaseBuilder builder) {
        profileResult = builder.profileResult;
        publicProfile = builder.publicProfile;
        rating = builder.rating;
        message = builder.message;
        languageCode = builder.languageCode;
        time = builder.time;
    }

    public ProfileResult getProfileResult() {
        return profileResult;
    }

    public boolean isPublicProfile() {
        return publicProfile;
    }

    public double getRating() {
        return rating;
    }

    public String getMessage() {
        return message;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public Date getTime() {
        return time;
    }

    protected static class BaseBuilder<T extends BaseBuilder<T>> {
        private ProfileResult profileResult;
        private boolean publicProfile;
        private double rating;
        private String message;
        private String languageCode;
        private Date time;

        protected BaseBuilder() {
        }

        public T profileResult(ProfileResult profileResult) {
            this.profileResult = profileResult;
            return (T) this;
        }

        public T publicProfile(boolean publicProfile) {
            this.publicProfile = publicProfile;
            return (T) this;
        }

        public T rating(double rating) {
            this.rating = rating;
            return (T) this;
        }

        public T message(String message) {
            this.message = message;
            return (T) this;
        }

        public T languageCode(String languageCode) {
            this.languageCode = languageCode;
            return (T) this;
        }

        public T time(Date time) {
            this.time = time;
            return (T) this;
        }

    }
}

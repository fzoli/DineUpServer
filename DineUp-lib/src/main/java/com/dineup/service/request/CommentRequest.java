package com.dineup.service.request;

import com.dineup.service.error.exception.InvalidCommentRequestException;

public class CommentRequest {
    private final Integer id;
    private final String message;
    private final Double rating;
    private final boolean publicProfile;

    private CommentRequest(Builder builder) {
        id = builder.id;
        message = builder.message;
        rating = builder.rating;
        publicProfile = builder.publicProfile;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public double getRating() {
        return rating;
    }

    public boolean isPublicProfile() {
        return publicProfile;
    }

    public void validate() {
        if (id == null || rating == null) {
            throw new InvalidCommentRequestException();
        }
    }
    
    public static final class Builder {
        private Integer id;
        private String message;
        private Double rating;
        private Boolean publicProfile;

        private Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder rating(Double rating) {
            this.rating = rating;
            return this;
        }

        public Builder publicProfile(Boolean publicProfile) {
            this.publicProfile = publicProfile;
            return this;
        }
        
        public CommentRequest build() {
            if (publicProfile == null) {
                publicProfile = true;
            }
            return new CommentRequest(this);
        }
    }
}

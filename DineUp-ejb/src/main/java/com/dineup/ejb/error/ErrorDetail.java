package com.dineup.ejb.error;

import com.dineup.service.error.ErrorKey;
import javax.ws.rs.core.Response;

public class ErrorDetail {

    private final Response.Status status;
    private final ErrorKey key;
    private final String note;
    private final ErrorMessage message;
    private final ErrorMessage localizedMessage;

    private ErrorDetail(Builder builder) {
        if (builder.status == null) {
            throw new IllegalArgumentException("status is null");
        }
        if (builder.key == null) {
            throw new IllegalArgumentException("key is null");
        }
        status = builder.status;
        key = builder.key;
        note = builder.note;
        message = builder.message;
        localizedMessage = builder.localizedMessage;
    }

    public Response.Status getStatus() {
        return status;
    }

    public ErrorKey getKey() {
        return key;
    }

    public String getNote() {
        return note;
    }

    public ErrorMessage getMessage() {
        return message;
    }

    public ErrorMessage getLocalizedMessage() {
        return localizedMessage;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Response.Status status;
        private ErrorKey key;
        private String note;
        private ErrorMessage message;
        private ErrorMessage localizedMessage;

        private Builder() {
        }

        public Builder status(Response.Status status) {
            this.status = status;
            return this;
        }

        public Builder key(ErrorKey key) {
            this.key = key;
            return this;
        }

        public Builder note(String note) {
            this.note = note;
            return this;
        }

        public Builder message(ErrorMessage message) {
            this.message = message;
            return this;
        }

        public Builder localizedMessage(ErrorMessage localizedMessage) {
            this.localizedMessage = localizedMessage;
            return this;
        }

        public ErrorDetail build() {
            return new ErrorDetail(this);
        }
    }
}

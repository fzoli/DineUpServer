package com.dineup.api.service.error;

public class ErrorDetail {
    
    private final ErrorKey key;
    private final String note;
    private final ErrorMessage message;
    private final ErrorMessage localizedMessage;

    private ErrorDetail(Builder builder) {
        if (builder.key == null) {
            throw new IllegalArgumentException("key is null");
        }
        key = builder.key;
        note = builder.note;
        message = builder.message;
        localizedMessage = builder.localizedMessage;
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
        private ErrorKey key;
        private String note;
        private ErrorMessage message;
        private ErrorMessage localizedMessage;

        private Builder() {
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
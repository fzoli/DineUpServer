package com.dineup.dom;

public final class Message {

    private final String text;
    private final String languageCode;

    public Message(String text, String languageCode) {
        this.text = text;
        this.languageCode = languageCode;
    }

    public String getText() {
        return text;
    }

    public String getLanguageCode() {
        return languageCode;
    }
    
}

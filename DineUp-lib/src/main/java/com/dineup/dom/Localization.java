package com.dineup.dom;

public class Localization {
    
    private final String languageCode;
    private final String currency;

    public Localization(String languageCode, String currency) {
        this.languageCode = languageCode;
        this.currency = currency;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getCurrency() {
        return currency;
    }
    
}

package com.dineup.dom;

public class Locale {
    
    private final String languageCode;
    private final String currency;

    public Locale(String languageCode, String currency) {
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

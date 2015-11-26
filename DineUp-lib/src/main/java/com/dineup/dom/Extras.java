package com.dineup.dom;

public class Extras {

    public static ExtraLocale getLocale(Extra extra, String languageCode) {
        if (extra == null) {
            return null;
        }
        return Locales.getLocale(extra.getLocales(), languageCode);
    }

    private Extras() {
    }

}

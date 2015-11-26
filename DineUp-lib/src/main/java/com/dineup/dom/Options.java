package com.dineup.dom;

public class Options {

    public static OptionLocale getLocale(Option option, String languageCode) {
        if (option == null) {
            return null;
        }
        return Locales.getLocale(option.getLocales(), languageCode);
    }

    private Options() {
    }

}

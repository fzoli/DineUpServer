package com.dineup.dom;

import java.util.List;

public class Locales {

    public static <L extends Locale, T extends LocalizedObject<L>> L getLocale(T obj, String languageCode) {
        return getLocale(obj.getLocales(), languageCode);
    }
    
    public static <T extends Locale> T getLocale(List<T> locales, String languageCode) {
        if (languageCode == null) {
            return null;
        }
        if (locales == null) {
            return null;
        }
        for (T locale : locales) {
            if (languageCode.equalsIgnoreCase(locale.getLanguageCode())) {
                return locale;
            }
        }
        return null;
    }

    private Locales() {
    }

}

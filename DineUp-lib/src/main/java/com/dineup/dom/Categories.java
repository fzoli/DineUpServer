package com.dineup.dom;

public class Categories {

    public static CategoryLocale getLocale(Category category, String languageCode) {
        if (category == null) {
            return null;
        }
        return Locales.getLocale(category.getLocales(), languageCode);
    }

    private Categories() {
    }

}

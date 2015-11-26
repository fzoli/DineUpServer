package com.dineup.dom;

public class Foods {

    public static FoodLocale getLocale(Food food, String languageCode) {
        if (food == null) {
            return null;
        }
        return Locales.getLocale(food.getLocales(), languageCode);
    }

    private Foods() {
    }

}

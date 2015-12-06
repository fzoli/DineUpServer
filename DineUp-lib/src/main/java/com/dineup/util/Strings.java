package com.dineup.util;

import java.util.Collection;

public class Strings {
    
    private static final StringFormatter stringFormatter = new StringFormatter();
    
    public static boolean isEmptyString(String string) {
        return string == null || string.isEmpty();
    }
    
    public static boolean isEmptyText(String string) {
        return string == null || string.trim().isEmpty();
    }
    
    public static String concat(Collection<String> texts, String delimiter) {
        return concat(texts, delimiter, stringFormatter);
    }
    
    public static <T> String concat(Collection<T> items, String delimiter, Formatter<T> formatter) {
        StringBuilder builder = new StringBuilder();
        int size = items.size();
        int count = 0;
        for (T item : items) {
            count++;
            boolean lastItem = count == size;
            String text = formatter.format(item);
            if (Strings.isEmptyText(text)) {
                continue;
            }
            builder.append(text);
            if (!lastItem) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }
    
    public static interface Formatter<T> {
        public String format(T item);
    }
    
    private static class StringFormatter implements Formatter<String> {
        @Override
        public String format(String item) {
            return item;
        }
    }
    
}

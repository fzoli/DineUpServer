package com.dineup.util;

import java.util.Arrays;
import java.util.List;

public class Strings {
    
    public static boolean isEmptyString(String string) {
        return string == null || string.isEmpty();
    }
    
    public static boolean isEmptyText(String string) {
        return string == null || string.trim().isEmpty();
    }
    
    public static String trim(String string, String delimiter) {
        if (string == null) {
            return "";
        }
        string = string.replaceFirst("^" + delimiter + "*", "");
        string = string.replaceFirst(delimiter + "*$", "");
        return string;
    }
    
    public static String concat(String delimiter, String ... texts) {
        return concat(delimiter, Arrays.asList(texts));
    }
    
    public static String concat(String delimiter, List<String> texts) {
        return concat(delimiter, texts, StringFormatter.INSTANCE);
    }
    
    public static <T> String concat(String delimiter, List<T> items, Formatter<T> formatter) {
        StringBuilder builder = new StringBuilder();
        int size = items.size();
        for (int i = 0; i < size; i++) {
            T item = items.get(i);
            
            String text = formatter.format(item);
            text = Strings.trim(text, delimiter);
            if (Strings.isEmptyText(text)) {
                continue;
            }
            
            T nextItem;
            String nextText = "";
            int nextIndex = i + 1;
            while (nextIndex < size) {
                nextItem = items.get(nextIndex);
                nextText = formatter.format(nextItem);
                nextText = Strings.trim(nextText, delimiter);
                if (!Strings.isEmptyText(nextText)) {
                    break;
                }
                nextIndex++;
            }
            
            builder.append(text);
            
            if (!Strings.isEmptyText(nextText)) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }
    
    public static interface Formatter<T> {
        public String format(T item);
    }
    
    private static class StringFormatter implements Formatter<String> {
        
        public static final StringFormatter INSTANCE = new StringFormatter();
        
        @Override
        public String format(String item) {
            return item;
        }
   
    }
    
}

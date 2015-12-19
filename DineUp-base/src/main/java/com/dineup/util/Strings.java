package com.dineup.util;

import com.dineup.util.string.Concatenator;
import com.dineup.util.string.Formatter;
import com.dineup.util.string.StringConcatenator;
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
        return new StringConcatenator(delimiter)
                .addItems(texts)
                .toString();
    }
    
    public static String concat(String delimiter, List<String> texts) {
        return new StringConcatenator(delimiter)
                .addItems(texts)
                .toString();
    }
    
    public static <T> String concat(String delimiter, List<T> items, Formatter<T> formatter) {
        return new Concatenator<>(delimiter, formatter)
                .addItems(items)
                .toString();
    }
    
}

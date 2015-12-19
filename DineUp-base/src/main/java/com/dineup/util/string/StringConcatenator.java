package com.dineup.util.string;

public final class StringConcatenator extends Concatenator<String> {
    public StringConcatenator(String delimiter) {
        super(delimiter, StringFormatter.INSTANCE);
    }
}

package com.dineup.util.string;

class StringFormatter implements Formatter<String> {
        
    public static final StringFormatter INSTANCE = new StringFormatter();

    @Override
    public String format(String item) {
        return item;
    }

}
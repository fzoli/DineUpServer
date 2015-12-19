package com.dineup.util.string;

import com.dineup.util.Strings;
import java.util.ArrayList;
import java.util.List;

public class Concatenator<T> {

    private final String delimiter;
    private final Formatter<T> formatter;
    private final List<T> items = new ArrayList<>();

    public Concatenator(String delimiter, Formatter<T> formatter) {
        if (delimiter == null) {
            throw new IllegalArgumentException("Delimiter is null");
        }
        if (formatter == null) {
            throw new IllegalArgumentException("Formatter is null");
        }
        this.delimiter = delimiter;
        this.formatter = formatter;
    }

    public Concatenator<T> addItem(T item) {
        if (item != null) {
            items.add(item);
        }
        return this;
    }

    public Concatenator<T> addItems(T ... items) {
        for (T item : items) {
            addItem(item);
        }
        return this;
    }

    public Concatenator<T> addItems(List<T> items) {
        for (T item : items) {
            addItem(item);
        }
        return this;
    }

    public void clear() {
        items.clear();
    }
    
    @Override
    public String toString() {
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

}
package com.dineup.util;

import java.util.AbstractList;
import java.util.List;
import java.util.ListIterator;

public class Lists {
    
    public static <T> void filter(List<T> list, Filter<T> ... filters) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            T object = it.next();
            for (Filter<T> filter : filters) {
                if (filter.isFiltered(object)) {
                    it.remove();
                }
            }
        }
    }
    
    public static <TCastTo, TCastFrom extends TCastTo> List<TCastTo> convert(final List<TCastFrom> list) {
        return new AbstractList<TCastTo>() {
            
            @Override
            public TCastTo get(int i) {
                return list.get(i);
            }

            @Override
            public int size() {
                return list.size();
            }
            
        };
    }

    private Lists() {
    }
    
}

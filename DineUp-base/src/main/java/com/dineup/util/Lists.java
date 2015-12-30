package com.dineup.util;

import java.io.Serializable;
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
        return new ConvertList<>(list);
    }

    private static class ConvertList<TCastTo, TCastFrom extends TCastTo> extends AbstractList<TCastTo> implements Serializable {
        
        private final List<TCastFrom> list;
        
        public ConvertList(List<TCastFrom> list) {
            this.list = list;
        }
        
        @Override
        public TCastTo get(int i) {
            return list.get(i);
        }

        @Override
        public boolean remove(Object o) {
            return list.remove(o);
        }

        @Override
        public TCastTo remove(int index) {
            return list.remove(index);
        }

        @Override
        public int size() {
            return list.size();
        }
        
    }
    
    private Lists() {
    }
    
}

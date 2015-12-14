package com.dineup.util;

import java.util.AbstractList;
import java.util.List;

public class Lists {
    
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

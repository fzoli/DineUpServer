package com.dineup.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Converters {
    
    public static final <In, Out> List<Out> convertList(Collection<In> list, Converter<In, Out> converter) {
        List<Out> outList = new ArrayList<>();
        if (list != null) {
            for (In in : list) {
                outList.add(converter.convert(in));
            }
        }
        return outList;
    }
    
    private Converters() {
    }
    
}

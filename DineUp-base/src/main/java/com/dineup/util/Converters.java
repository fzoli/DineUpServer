package com.dineup.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Converters {
    
    public static final <In, Out> Out convert(In in, Converter<In, Out> converter) {
        if (in == null) {
            return null;
        }
        return converter.convert(in);
    }
    
    public static final <In, Out> List<Out> convertList(Collection<In> list, Converter<In, Out> converter) {
        if (list == null) {
            return null;
        }
        List<Out> outList = new ArrayList<>();
        for (In in : list) {
            Out out = convert(in, converter);
            if (out != null) {
                outList.add(out);
            }
        }
        return outList;
    }
    
    private Converters() {
    }
    
}

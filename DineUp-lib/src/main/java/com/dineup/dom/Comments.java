package com.dineup.dom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Comments {

    public static List<Comment> getSortedComments(final List<Comment> comments, final String preferredLanguageCode) {
        // Create a modifiable copy of comments.
        List<Comment> list = new ArrayList<>(comments);
        // First sort by time.
        Collections.sort(list, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });
        // Then move the comments of the requested language to the beginning of the list.
        if (preferredLanguageCode != null) {
            Collections.sort(list, new Comparator<Comment>() {
                @Override
                public int compare(Comment o1, Comment o2) {
                    int o1Prio = getLanguageCode(o1).equalsIgnoreCase(preferredLanguageCode) ? -1 : 1;
                    int o2Prio = getLanguageCode(o2).equalsIgnoreCase(preferredLanguageCode) ? -1 : 1;
                    return Integer.compare(o1Prio, o2Prio);
                }
            });
        }
        return list;
    }
    
    private static String getLanguageCode(Comment comment) {
        if (comment == null || comment.getMessage() == null) {
            return null;
        }
        return comment.getMessage().getLanguageCode();
    }
    
    private Comments() {
    }
    
}

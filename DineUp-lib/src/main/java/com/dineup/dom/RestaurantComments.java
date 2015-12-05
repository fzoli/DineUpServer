package com.dineup.dom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RestaurantComments {

    public static List<RestaurantComment> getSortedComments(final List<RestaurantComment> comments, final String preferredLanguageCode) {
        // Create a modifiable copy of comments.
        List<RestaurantComment> list = new ArrayList<>(comments);
        // First sort by time.
        Collections.sort(list, new Comparator<RestaurantComment>() {
            @Override
            public int compare(RestaurantComment o1, RestaurantComment o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });
        // Then move the comments of the requested language to the beginning of the list.
        if (preferredLanguageCode != null) {
            Collections.sort(list, new Comparator<RestaurantComment>() {
                @Override
                public int compare(RestaurantComment o1, RestaurantComment o2) {
                    int o1Prio = o1.getLanguageCode().equalsIgnoreCase(preferredLanguageCode) ? -1 : 1;
                    int o2Prio = o2.getLanguageCode().equalsIgnoreCase(preferredLanguageCode) ? -1 : 1;
                    return Integer.compare(o1Prio, o2Prio);
                }
            });
        }
        return list;
    }
    
    private RestaurantComments() {
    }
    
}

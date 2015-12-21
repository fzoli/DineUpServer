package com.dineup.api.dom;

import java.util.List;

import com.dineup.api.dom.Person.Gender;

public interface Event {
	
    public enum EventType {
        DATE, TALKING,
    }

    public enum ActivityType {
        BREAKFAST, LUNCH, DINNER, DRINK, PARTY
    }

    public int getId();
    public Profile getProfile();
    public Restaurant getRestaurant();
    public String getDescription();
    public String getLanguageCode();
    public EventType getEventType();
    public ActivityType getActivityType();
    public TimeInterval getRange();
    public Gender getAcceptedGender();
    public List<Join> getJoinRequests();
    public List<Join> getAcceptedJoins();
        
}

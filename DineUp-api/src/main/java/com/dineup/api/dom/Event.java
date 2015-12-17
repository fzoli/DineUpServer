package com.dineup.api.dom;

import java.util.List;

import com.dineup.api.dom.Person.Gender;

public class Event {
	
	public enum RequestType {
		DATE, TALKING,
	}
	
	public enum ActivityType {
		BREAKFAST, LUNCH, DINNER, DRINK, PARTY
	}
	
	private final int requestId;
	private Profile profile;
	private Restaurant restaurant;
	private String description;
	private RequestType requestType;
	private ActivityType activityType;
	private Range range;
	private Gender acceptedGenders; //unknow can mean both is accepted
	private List<Join> joinsRequests;
	private List<Join> acceptedJoins;
}

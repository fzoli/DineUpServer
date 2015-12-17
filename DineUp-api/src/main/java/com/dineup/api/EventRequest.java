package com.dineup.api;

import java.util.Date;

import com.dineup.api.dom.Event.ActivityType;
import com.dineup.api.dom.Event.RequestType;
import com.dineup.api.dom.Person.Gender;
import com.dineup.api.dom.Restaurant;

public class EventRequest {
	private Restaurant restaurant;
	private String description;
	private RequestType requestType;
	private ActivityType activityType;
	private Date fromTime;
	private Date toTime;
	private Gender acceptedGenders; //unknow can mean both is accepted
}

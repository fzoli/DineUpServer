package com.dineup.servlet.message;

import java.util.List;

public class RestaurantMessage {
    public String id, name, type, photoUrl, description, openHours, address;
    public double latitude, longitude;
    public int rating;
    public List<CategoryMessage> categories;
}

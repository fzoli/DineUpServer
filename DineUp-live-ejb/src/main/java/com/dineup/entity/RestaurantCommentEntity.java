package com.dineup.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_comment")
public class RestaurantCommentEntity extends BaseCommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn(name = "restaurant", nullable = false)
    private RestaurantEntity restaurant;

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }
    
}

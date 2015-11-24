package com.dineup.entity;

import com.dineup.dom.Restaurant;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity implements Restaurant, Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer key;
    
    @Column(name="id", unique=true, nullable=false)
    private String id;

    public RestaurantEntity() {
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
    
    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}

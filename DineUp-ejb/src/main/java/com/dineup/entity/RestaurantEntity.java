package com.dineup.entity;

import com.dineup.dom.Restaurant;
import java.io.Serializable;
import java.util.List;
import java.util.Locale.Category;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity implements Restaurant, Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer key;
    
    @Column(name="id", unique = true, nullable = false)
    private String id;

    @Column(name="name", nullable = false)
    private String name; // TODO: locale?
    
    // TODO: it should be enum or an other entity
    @Column(name = "type", nullable = false)
    private String type;
    
    @Column(name = "photo_url", nullable = false)
    private String photoUrl;
    
    @Column(name = "description")
    private String description; // TODO: locale?
    
    // TODO: latitude and longitude is a Coordinate
    @Column(name = "latitude")
    private double latitude;
    
    @Column(name = "longitude")
    private double longitude;
    
    // TODO: isn't it calculated?
    @Column(name = "rating")
    private int rating;
    
    @Column(name = "openHours")
    private String openHours; // TODO: locale?
    
    @Column(name = "address")
    private String address;
    
    @ManyToMany
    @JoinTable(
      name="restaurant_categories",
      joinColumns={@JoinColumn(name="restaurant", referencedColumnName="key")},
      inverseJoinColumns={@JoinColumn(name="category", referencedColumnName="key")})
    private List<Category> categories;
    
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

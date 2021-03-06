package com.dineup.entity;

import com.dineup.dom.Category;
import com.dineup.common.dom.Coordinate;
import com.dineup.dom.Restaurant;
import com.dineup.dom.RestaurantLocale;
import com.dineup.util.Lists;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.dineup.dom.Comment;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity implements Restaurant, Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type", nullable = false)
    private String type;
    
    @Column(name = "photo_url", nullable = false)
    private String photoUrl;
    
    @Column(name = "latitude")
    private double latitude;
    
    @Column(name = "longitude")
    private double longitude;
    
    @Column(name = "rating")
    private double rating;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "default_currency", nullable = false)
    private String defaultCurrency;
    
    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantLocaleEntity> locales;
    
    @OneToMany(mappedBy = "restaurant")
    private List<CategoryEntity> categories;
    
    @OneToMany(mappedBy = "restaurant")
    private List<ExtraEntity> extras;
    
    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantCommentEntity> comments;
    
    public RestaurantEntity() {
    }
    
    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }
    
    @Override
    public Coordinate getCoordinate() {
        return new Coordinate(latitude, longitude);
    }
    
    public void setCoordinate(Coordinate coordinate) {
        setCoordinate(coordinate.getLatitude(), coordinate.getLongitude());
    }
    
    public void setCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    @Override
    public List<RestaurantLocale> getLocales() {
        return Lists.convert(locales);
    }

    @Override
    public List<Comment> getComments() {
        return Lists.convert(comments);
    }
    
    @Override
    public List<Category> getCategories() {
        return Lists.convert(categories);
    }

    public List<ExtraEntity> getExtras() {
        return extras;
    }
    
}

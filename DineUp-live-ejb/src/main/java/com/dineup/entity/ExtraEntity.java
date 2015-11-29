package com.dineup.entity;

import com.dineup.dom.Extra;
import com.dineup.dom.ExtraLocale;
import com.dineup.dom.Food;
import com.dineup.dom.Option;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "extra")
public class ExtraEntity implements Extra, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "restaurant", nullable = false)
    private RestaurantEntity restaurant;
    
    @Column(name = "type", nullable = false)
    private String type;
    
    @OneToMany(mappedBy = "extra")
    private List<ExtraLocaleEntity> locales;
    
    @OneToMany(mappedBy = "extra")
    private List<OptionEntity> options;

    @ManyToMany
    @JoinTable(
      name="food_extras",
      joinColumns={@JoinColumn(name="extra", referencedColumnName="id")},
      inverseJoinColumns={@JoinColumn(name="food", referencedColumnName="id")})
    private List<FoodEntity> foods;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public List<ExtraLocale> getLocales() {
        return (List) locales;
    }

    @Override
    public List<Option> getOptions() {
        return (List) options;
    }

    public List<Food> getFoods() {
        return (List) foods;
    }

}

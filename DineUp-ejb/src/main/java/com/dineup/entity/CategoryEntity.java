package com.dineup.entity;

import com.dineup.dom.Category;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity implements Category, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "restaurant", nullable = false)
    private RestaurantEntity restaurant;
    
    @Column(name = "photo_url", nullable = false)
    private String photoUrl;
    
    @OneToMany(mappedBy = "category")
    private List<CategoryLocaleEntity> locales;
    
    @OneToMany(mappedBy = "category")
    private List<FoodEntity> foods;
    
}

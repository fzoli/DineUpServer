package com.dineup.entity;

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
@Table(name = "food")
public class FoodEntity {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private CategoryEntity category;
    
    @Column(name = "photo_url", nullable = false)
    private String photoUrl;
    
    @OneToMany(mappedBy = "food")
    private List<FoodLocaleEntity> locales;
    
    @OneToMany(mappedBy = "food")
    private List<FoodPriceEntity> prices;
    
    @ManyToMany
    @JoinTable(
      name="food_extras",
      joinColumns={@JoinColumn(name="food", referencedColumnName="id")},
      inverseJoinColumns={@JoinColumn(name="extra", referencedColumnName="id")})
    private List<ExtraEntity> extras;
    
}

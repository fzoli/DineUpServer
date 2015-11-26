package com.dineup.entity;

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
@Table(name = "extra")
public class ExtraEntity {
    
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
    
}

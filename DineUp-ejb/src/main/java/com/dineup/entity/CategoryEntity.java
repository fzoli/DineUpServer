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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity implements Category, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer key;
    
    @Column(name="id", unique = true, nullable = false)
    private String id;
    
    @Column(name="name", nullable = false)
    private String name;
    
    @Column(name = "photo_url", nullable = false)
    private String photoUrl;
    
    @ManyToMany
    @JoinTable(
      name="restaurant_categories",
      joinColumns={@JoinColumn(name="restaurant", referencedColumnName="key")},
      inverseJoinColumns={@JoinColumn(name="category", referencedColumnName="key")})
    private List<?> items;
    
}

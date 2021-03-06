package com.dineup.entity;

import com.dineup.dom.Comment;
import com.dineup.dom.Extra;
import com.dineup.dom.Food;
import com.dineup.dom.FoodLocale;
import com.dineup.dom.Price;
import com.dineup.util.Lists;

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
@Table(name = "food")
public class FoodEntity implements Food, Serializable {

    private static final long serialVersionUID = 1L;

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
    
    @OneToMany(mappedBy = "food")
    private List<FoodCommentEntity> comments;
    
    @ManyToMany
    @JoinTable(
      name="food_extras",
      joinColumns={@JoinColumn(name="food", referencedColumnName="id")},
      inverseJoinColumns={@JoinColumn(name="extra", referencedColumnName="id")})
    private List<ExtraEntity> extras;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public List<FoodLocale> getLocales() {
        return Lists.convert(locales);
    }

    @Override
    public List<Price> getPrices() {
        return Lists.convert(prices);
    }

    @Override
    public List<Extra> getExtras() {
        return Lists.convert(extras);
    }

    @Override
    public List<Comment> getComments() {
        return Lists.convert(comments);
    }

}

package com.dineup.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "food_comment")
public class FoodCommentEntity extends BaseCommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn(name = "food", nullable = false)
    private FoodEntity food;

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }
    
}

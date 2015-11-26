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
@Table(name = "option")
public class OptionEntity {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "extra", nullable = false)
    private ExtraEntity extra;
    
    @OneToMany(mappedBy = "option")
    private List<OptionLocaleEntity> locales;
    
    @OneToMany(mappedBy = "option")
    private List<OptionPriceEntity> prices;
    
}

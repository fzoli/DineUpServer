package com.dineup.entity;

import com.dineup.dom.Option;
import com.dineup.dom.OptionLocale;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "option")
public class OptionEntity implements Option, Serializable {

    private static final long serialVersionUID = 1L;

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

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ExtraEntity getExtra() {
        return extra;
    }

    public void setExtra(ExtraEntity extra) {
        this.extra = extra;
    }

    @Override
    public List<Price> getPrices() {
        return Lists.convert(prices);
    }

    @Override
    public List<OptionLocale> getLocales() {
        return Lists.convert(locales);
    }

}

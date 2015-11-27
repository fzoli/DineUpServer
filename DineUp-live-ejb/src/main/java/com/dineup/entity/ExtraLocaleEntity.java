package com.dineup.entity;

import com.dineup.dom.ExtraLocale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "extra_locale")
public class ExtraLocaleEntity implements ExtraLocale, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "extra", nullable = false)
    private ExtraEntity extra;
    
    @Column(name = "language_code", nullable = false)
    private String languageCode;
    
    @Column(name = "name", nullable = false)
    private String name;

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
    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

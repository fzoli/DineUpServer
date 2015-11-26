package com.dineup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "extra_locale")
public class ExtraLocaleEntity {
    
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
    
}

package com.dineup.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import com.dineup.dom.Comment;

@Entity
@Table(name = "food_comment")
public class FoodCommentEntity implements Comment, Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "food", nullable = false)
    private FoodEntity food;
    
    @ManyToOne
    @JoinColumn(name = "profile", nullable = false)
    private ProfileEntity profile;

    @Column(name = "public_profile", nullable = false)
    private boolean publicProfile;
    
    @Column(name = "rating", nullable = false)
    private double rating;
    
    @Column(name = "time", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date time;
    
    @Column(name = "message")
    private String message;
    
    @Column(name = "language_code")
    private String languageCode;
    
    @Override
    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    @Override
    public boolean isProfilePublic() {
        return publicProfile;
    }

    public void setPublicProfile(boolean publicProfile) {
        this.publicProfile = publicProfile;
    }

    @Override
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @Override
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
}

package com.dineup.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import com.dineup.dom.Comment;
import com.dineup.dom.Message;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseCommentEntity implements Comment {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
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
    
    @Column(name = "message_text")
    private String messageText;
    
    @Column(name = "message_language_code")
    private String messageLanguageCode;

    @Override
    public Integer getId() {
        return id;
    }
    
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
    public Message getMessage() {
        return new Message(messageText, messageLanguageCode);
    }

    public void setMessage(Message message) {
        if (message == null) {
            messageText = null;
            messageLanguageCode = null;
        }
        else {
            messageText = message.getText();
            messageLanguageCode = message.getLanguageCode();
        }
    }
    
    @Override
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
}

package com.dineup.entity;

import com.dineup.dom.Person;
import com.dineup.dom.Person.Name;
import com.dineup.dom.Profile;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "profile")
public class ProfileEntity implements Profile, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_sync")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastSync;
    
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Profile.Type type;
    
    @Column(name = "user_id", nullable = false)
    private String userId;
    
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Person.Gender gender;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "middle_name")
    private String middleName;
    
    @Column(name = "birth_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthDate;

    @OneToMany(mappedBy = "profile")
    private List<RestaurantCommentEntity> restaurantComments;
    
    private transient final ProfilePerson profilePerson = new ProfilePerson();
    private transient final ProfileName profileName = new ProfileName();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public Date getLastSync() {
        return lastSync;
    }

    public void setLastSync(Date lastSync) {
        this.lastSync = lastSync;
    }
    
    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    @Override
    public ProfilePerson getPerson() {
        return profilePerson;
    }

    public List<RestaurantCommentEntity> getRestaurantComments() {
        return restaurantComments;
    }

    public final class ProfilePerson implements Person {

        private ProfilePerson() {
        }

        @Override
        public ProfileName getName() {
            return profileName;
        }

        @Override
        public Gender getGender() {
            return gender;
        }

        public void setGender(Person.Gender sex) {
            ProfileEntity.this.gender = sex;
        }
        
        @Override
        public Date getBirthDate() {
            return birthDate;
        }
        
        public void setBirthDate(Date birthDate) {
            ProfileEntity.this.birthDate = birthDate;
        }
        
    }

    public final class ProfileName implements Name {

        private ProfileName() {
        }
        
        @Override
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            ProfileEntity.this.firstName = firstName;
        }
        
        @Override
        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            ProfileEntity.this.middleName = middleName;
        }
        
        @Override
        public String getLastName() {
            return lastName;
        }
        
        public void setLastName(String lastName) {
            ProfileEntity.this.lastName = lastName;
        }
        
    }
    
}

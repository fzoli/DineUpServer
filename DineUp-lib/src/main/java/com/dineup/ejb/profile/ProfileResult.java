package com.dineup.ejb.profile;

import com.dineup.dom.Person;
import java.util.Date;

public class ProfileResult {

    private final String userId;
    private final Person.Gender gender;
    private final Date birthDate;
    private final String firstName;
    private final String middleName;
    private final String lastName;

    private ProfileResult(Builder builder) {
        userId = builder.userId;
        gender = builder.gender;
        birthDate = builder.birthDate;
        firstName = builder.firstName;
        middleName = builder.middleName;
        lastName = builder.lastName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUserId() {
        return userId;
    }

    public Person.Gender getGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public static final class Builder {

        private String userId;
        private Person.Gender gender;
        private Date birthDate;
        private String firstName;
        private String middleName;
        private String lastName;

        private Builder() {
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder gender(Person.Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder birthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ProfileResult build() {
            return new ProfileResult(this);
        }

    }

}

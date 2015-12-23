package com.dineup.mock;

import com.dineup.dom.Person;
import com.dineup.dom.Profile;
import com.dineup.ejb.db.RestaurantDataSource;
import com.dineup.ejb.profile.ProfileManagerFactory;
import java.util.Calendar;
import java.util.Date;

public class MockProfile implements Profile {
    private final RestaurantDataSource dataSource;
    private final ProfileManagerFactory factory;
    private final int id;

    public MockProfile(RestaurantDataSource dataSource, ProfileManagerFactory factory, int id) {
        this.dataSource = dataSource;
        this.factory = factory;
        this.id = id;
    }
    
    @Override
    public Date getLastSync() {
        return new Date();
    }

    @Override
    public String getUserId() {
        return "UID"+id;
    }

    @Override
    public Profile.Type getType() {
        return id % 2 == 0 ? Type.GOOGLE_PLUS : Type.FACEBOOK;
    }

    @Override
    public Person getPerson() {
        return new Person() {
            @Override
            public Person.Name getName() {
                return new Person.Name() {

                    @Override
                    public String getFirstName() {
                        return "First";
                    }

                    @Override
                    public String getMiddleName() {
                        return "Middle";
                    }

                    @Override
                    public String getLastName() {
                        return "Last";
                    }
                };
            }

            @Override
            public Person.Gender getGender() {
                return Person.Gender.MALE;
            }

            @Override
            public Date getBirthDate() {
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(0);
                c.set(Calendar.YEAR, 1989);
                c.set(Calendar.MONTH, Calendar.OCTOBER);
                c.set(Calendar.DAY_OF_MONTH, 27);
                return c.getTime();
            }
        };
    }
    
}

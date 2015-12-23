package com.dineup.mock;

import com.dineup.dom.Profile;
import com.dineup.ejb.db.MockDatas;
import com.dineup.ejb.db.RestaurantDataSource;
import com.dineup.ejb.profile.ProfileManagerFactory;
import java.util.Calendar;
import java.util.Date;
import com.dineup.dom.Comment;

public class MockComment implements Comment, MockDatas {

    private final RestaurantDataSource dataSource;
    private final ProfileManagerFactory factory;
    private final int id;
    
    public MockComment(RestaurantDataSource dataSource, ProfileManagerFactory factory, int id) {
        this.factory = factory;
        this.dataSource = dataSource;
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
    
    @Override
    public Profile getProfile() {
        return new MockProfile(dataSource, factory, id);
    }

    @Override
    public boolean isProfilePublic() {
        return id % 3 == 0;
    }

    @Override
    public double getRating() {
        return id * 1.5;
    }

    @Override
    public String getMessage() {
        if (id % 2 == 0) {
            return String.format("Test message %s", id);
        }
        return null;
    }

    @Override
    public String getLanguageCode() {
        return "en";
    }

    @Override
    public Date getTime() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, -1 * id);
        return c.getTime();
    }

}

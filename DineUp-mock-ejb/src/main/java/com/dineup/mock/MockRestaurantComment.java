package com.dineup.mock;

import com.dineup.dom.Person;
import com.dineup.dom.Profile;
import com.dineup.dom.RestaurantComment;
import com.dineup.ejb.db.MockDatas;
import com.dineup.ejb.db.RestaurantDataSource;
import java.util.Calendar;
import java.util.Date;

public class MockRestaurantComment implements RestaurantComment, MockDatas {

    private final RestaurantDataSource dataSource;
    private final int id;
    
    
    public MockRestaurantComment(RestaurantDataSource dataSource, int id) {
        this.dataSource = dataSource;
        this.id = id;
    }

    @Override
    public Profile getProfile() {
        return new Profile() {
            
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
                return Type.GOOGLE_PLUS;
            }

            @Override
            public Person getPerson() {
                return new Person() {
                    @Override
                    public Person.Name getName() {
                        return new Name() {

                            @Override
                            public String getTitle() {
                                return "title";
                            }

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
                    public Person.Sex getSex() {
                        return Sex.MALE;
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

            @Override
            public String getPhotoUrl() {
                return String.format(IMAGE_URL_FORMAT, "Photo"+id);
            }
            
        };
    }

    @Override
    public boolean isProfilePublic() {
        return id % 2 == 0;
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

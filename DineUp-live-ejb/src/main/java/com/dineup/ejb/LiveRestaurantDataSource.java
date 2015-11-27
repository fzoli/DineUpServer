package com.dineup.ejb;

import com.dineup.dom.Restaurant;
import com.dineup.entity.RestaurantEntity;
import com.dineup.entity.RestaurantEntity_;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Singleton
public class LiveRestaurantDataSource implements RestaurantDataSource {

    @PersistenceContext
    private EntityManager manager;

    private EntityManager getManager() {
        return manager;
    }
    
    @Override
    public List<Restaurant> getRestaurants() {
        CriteriaBuilder builder = getManager().getCriteriaBuilder();
        CriteriaQuery<RestaurantEntity> query = builder.createQuery(RestaurantEntity.class);
        Root<RestaurantEntity> root = query.from(RestaurantEntity.class);
        query.orderBy(
            builder.asc(root.get(RestaurantEntity_.id))
        );
        List<RestaurantEntity> restaurants = getManager().createQuery(query).getResultList();
        return (List) restaurants;
    }

}

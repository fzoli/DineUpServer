package com.dineup.ejb.db;

import com.dineup.dom.Category;
import com.dineup.dom.Extra;
import com.dineup.dom.Food;
import com.dineup.dom.Option;
import com.dineup.dom.Restaurant;
import com.dineup.entity.CategoryEntity;
import com.dineup.entity.CategoryEntity_;
import com.dineup.entity.ExtraEntity;
import com.dineup.entity.ExtraEntity_;
import com.dineup.entity.FoodEntity;
import com.dineup.entity.FoodEntity_;
import com.dineup.entity.OptionEntity;
import com.dineup.entity.OptionEntity_;
import com.dineup.entity.RestaurantCommentEntity;
import com.dineup.entity.RestaurantCommentEntity_;
import com.dineup.entity.RestaurantEntity;
import com.dineup.entity.RestaurantEntity_;
import com.dineup.util.Filter;
import com.dineup.util.Lists;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import com.dineup.dom.Comment;
import com.dineup.dom.Profile;
import com.dineup.ejb.db.data.BaseCommentData;
import com.dineup.ejb.db.data.FoodCommentData;
import com.dineup.ejb.db.data.RestaurantCommentData;
import com.dineup.ejb.profile.ProfileResult;
import com.dineup.entity.BaseCommentEntity;
import com.dineup.entity.FoodCommentEntity;
import com.dineup.entity.FoodCommentEntity_;
import com.dineup.entity.ProfileEntity;
import com.dineup.entity.ProfileEntity_;
import java.util.Date;
import javax.persistence.NoResultException;

@Singleton
public class LiveRestaurantDataSource implements RestaurantDataSource {

    @PersistenceContext
    private EntityManager manager;
    
    @PreDestroy
    public void destruct() {
        manager.close(); // Support hot deploy
    }
    
    private EntityManager getManager() {
        return manager;
    }
    
    public Profile getProfile(String userId, Profile.Type profileType) {
        CriteriaBuilder builder = getManager().getCriteriaBuilder();
        CriteriaQuery<ProfileEntity> query = builder.createQuery(ProfileEntity.class);
        Root<ProfileEntity> root = query.from(ProfileEntity.class);
        query.where(
            builder.and(
                builder.equal(root.get(ProfileEntity_.userId), userId),
                builder.equal(root.get(ProfileEntity_.type), profileType)
            )
        );
        try {
            ProfileEntity result = getManager().createQuery(query).getSingleResult();
            return result;
        }
        catch (NoResultException ex) {
            return null;
        }
    }
    
    @Override
    public Restaurant getRestaurant(int restaurantId) {
        CriteriaBuilder builder = getManager().getCriteriaBuilder();
        CriteriaQuery<RestaurantEntity> query = builder.createQuery(RestaurantEntity.class);
        Root<RestaurantEntity> root = query.from(RestaurantEntity.class);
        query.where(builder.equal(root.get(RestaurantEntity_.id), restaurantId));
        try {
            RestaurantEntity result = getManager().createQuery(query).getSingleResult();
            return result;
        }
        catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Restaurant> getRestaurants(Filter<Restaurant> ... filters) {
        CriteriaBuilder builder = getManager().getCriteriaBuilder();
        CriteriaQuery<RestaurantEntity> query = builder.createQuery(RestaurantEntity.class);
        Root<RestaurantEntity> root = query.from(RestaurantEntity.class);
        query.orderBy(builder.asc(root.get(RestaurantEntity_.id)));
        List<RestaurantEntity> resultList = getManager().createQuery(query).getResultList();
        List<Restaurant> list = Lists.convert(resultList);
        Lists.filter(list, filters);
        return list;
    }

    @Override
    public List<Comment> getRestaurantComments(int restaurantId) {
        CriteriaBuilder builder = getManager().getCriteriaBuilder();
        CriteriaQuery<RestaurantCommentEntity> query = builder.createQuery(RestaurantCommentEntity.class);
        Root<RestaurantCommentEntity> root = query.from(RestaurantCommentEntity.class);
        query.where(builder.equal(root.get(RestaurantCommentEntity_.restaurant).get(RestaurantEntity_.id), restaurantId));
        List<RestaurantCommentEntity> resultList = getManager().createQuery(query).getResultList();
        return Lists.convert(resultList);
    }
    
    @Override
    public List<Category> getCategories(int restaurantId) {
        CriteriaBuilder builder = getManager().getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
        Root<CategoryEntity> root = query.from(CategoryEntity.class);
        query.orderBy(builder.asc(root.get(CategoryEntity_.id)));
        query.where(builder.equal(root.get(CategoryEntity_.restaurant).get(RestaurantEntity_.id), restaurantId));
        List<CategoryEntity> resultList = getManager().createQuery(query).getResultList();
        return Lists.convert(resultList);
    }

    @Override
    public Food getFood(int foodId) {
        CriteriaBuilder builder = getManager().getCriteriaBuilder();
        CriteriaQuery<FoodEntity> query = builder.createQuery(FoodEntity.class);
        Root<FoodEntity> root = query.from(FoodEntity.class);
        query.where(builder.equal(root.get(FoodEntity_.id), foodId));
        try {
            FoodEntity result = getManager().createQuery(query).getSingleResult();
            return result;
        }
        catch (NoResultException ex) {
            return null;
        }
    }
    
    @Override
    public List<Food> getFoods(int categoryId) {
        CriteriaBuilder builder = getManager().getCriteriaBuilder();
        CriteriaQuery<FoodEntity> query = builder.createQuery(FoodEntity.class);
        Root<FoodEntity> root = query.from(FoodEntity.class);
        query.orderBy(builder.asc(root.get(FoodEntity_.id)));
        query.where(builder.equal(root.get(FoodEntity_.category).get(CategoryEntity_.id), categoryId));
        List<FoodEntity> resultList = getManager().createQuery(query).getResultList();
        return Lists.convert(resultList);
    }

    @Override
    public List<Comment> getFoodComments(int foodId) {
        CriteriaBuilder builder = getManager().getCriteriaBuilder();
        CriteriaQuery<FoodCommentEntity> query = builder.createQuery(FoodCommentEntity.class);
        Root<FoodCommentEntity> root = query.from(FoodCommentEntity.class);
        query.where(builder.equal(root.get(FoodCommentEntity_.food).get(FoodEntity_.id), foodId));
        List<FoodCommentEntity> resultList = getManager().createQuery(query).getResultList();
        return Lists.convert(resultList);
    }
    
    @Override
    public List<Extra> getExtras(int foodId) {
        CriteriaBuilder builder = getManager().getCriteriaBuilder();
        CriteriaQuery<ExtraEntity> query = builder.createQuery(ExtraEntity.class);
        Root<ExtraEntity> root = query.from(ExtraEntity.class);
        ListJoin<ExtraEntity, FoodEntity> join = root.join(ExtraEntity_.foods);
        query.orderBy(builder.asc(root.get(ExtraEntity_.id)));
        query.where(builder.equal(join.get(FoodEntity_.id), foodId));
        List<ExtraEntity> resultList = getManager().createQuery(query).getResultList();
        return Lists.convert(resultList);
    }

    @Override
    public List<Option> getOptions(int extraId) {
        CriteriaBuilder builder = getManager().getCriteriaBuilder();
        CriteriaQuery<OptionEntity> query = builder.createQuery(OptionEntity.class);
        Root<OptionEntity> root = query.from(OptionEntity.class);
        query.orderBy(builder.asc(root.get(OptionEntity_.id)));
        query.where(builder.equal(root.get(OptionEntity_.extra).get(ExtraEntity_.id), extraId));
        List<OptionEntity> resultList = getManager().createQuery(query).getResultList();
        return Lists.convert(resultList);
    }

    @Override
    public int addRestaurantComment(RestaurantCommentData commentData) {
        RestaurantCommentEntity commentEntity = new RestaurantCommentEntity();
        loadBaseComment(commentEntity, commentData);
        commentEntity.setRestaurant((RestaurantEntity) commentData.getRestaurant());
        getManager().persist(commentEntity);
        return commentEntity.getId();
    }
    
    @Override
    public int addFoodComment(FoodCommentData commentData) {
        FoodCommentEntity commentEntity = new FoodCommentEntity();
        loadBaseComment(commentEntity, commentData);
        commentEntity.setFood((FoodEntity) commentData.getFood());
        getManager().persist(commentEntity);
        return commentEntity.getId();
    }

    private void loadBaseComment(BaseCommentEntity commentEntity, BaseCommentData commentData) {
        ProfileResult profileResult = commentData.getProfileResult();
        ProfileEntity profileEntity = updateProfile(profileResult);
        commentEntity.setMessage(commentData.getMessage());
        commentEntity.setProfile(profileEntity);
        commentEntity.setPublicProfile(commentData.isPublicProfile());
        commentEntity.setRating(commentData.getRating());
        commentEntity.setTime(commentData.getTime());
    }
    
    private ProfileEntity updateProfile(ProfileResult profileResult) {
        ProfileEntity profileEntity = (ProfileEntity) getProfile(profileResult.getUserId(), profileResult.getType());
        if (profileEntity == null) {
            profileEntity = new ProfileEntity();
        }
        profileEntity.setLastSync(new Date());
        profileEntity.setType(profileResult.getType());
        profileEntity.setUserId(profileResult.getUserId());
        profileEntity.getPerson().setGender(profileResult.getGender());
        profileEntity.getPerson().setBirthDate(profileResult.getBirthDate());
        profileEntity.getPerson().getName().setFirstName(profileResult.getFirstName());
        profileEntity.getPerson().getName().setLastName(profileResult.getLastName());
        profileEntity.getPerson().getName().setMiddleName(profileResult.getMiddleName());
        getManager().persist(profileEntity);
        return profileEntity;
    }
    
}

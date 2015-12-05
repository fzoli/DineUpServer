package com.dineup.rest.element;

import com.dineup.dom.Coordinate;
import com.dineup.dom.Coordinates;
import com.dineup.dom.Restaurant;
import com.dineup.dom.RestaurantComment;
import com.dineup.dom.RestaurantComments;
import com.dineup.dom.RestaurantLocale;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.converter.CategoryElementConverter;
import com.dineup.rest.element.converter.CoordinateElementConverter;
import com.dineup.rest.element.converter.RestaurantCommentElementConverter;
import com.dineup.util.Converters;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "restaurant")
public class RestaurantElement {

    private ElementConfig elementConfig;
    private Restaurant restaurant;

    public RestaurantElement() {
        // JAXB needs this
    }

    public RestaurantElement(ElementConfig elementConfig, Restaurant restaurant) {
        this.elementConfig = elementConfig;
        this.restaurant = restaurant;
    }
    
    private RestaurantLocale getLocale() {
        return elementConfig.getLocale(restaurant);
    }
    
    @XmlAttribute
    public Integer getId() {
        return restaurant.getId();
    }

    @XmlElement
    public Double getDistance() {
        Coordinate restaurantCoordinate = restaurant.getCoordinate();
        Coordinate configCoordinate = elementConfig.createCoordinate();
        return Coordinates.getDistanceInMeter(restaurantCoordinate, configCoordinate);
    }
    
    @XmlElement
    public String getType() {
        return restaurant.getType();
    }

    @XmlElement
    public String getName() {
        RestaurantLocale l = getLocale();
        return l == null ? null : l.getName();
    }

    @XmlElement
    public String getDescription() {
        RestaurantLocale l = getLocale();
        return l == null ? null : l.getDescription();
    }

    @XmlElement
    public String getOpenHours() {
        RestaurantLocale l = getLocale();
        return l == null ? null : l.getOpenHours();
    }

    @XmlElement
    public String getPhotoUrl() {
        return restaurant.getPhotoUrl();
    }

    @XmlElement
    public String getAddress() {
        return restaurant.getAddress();
    }

    @XmlElement
    public String getDefaultCurrency() {
        return restaurant.getDefaultCurrency();
    }

    @XmlElement
    public CoordinateElement getCoordinate() {
        return Converters.convert(restaurant.getCoordinate(), new CoordinateElementConverter(elementConfig));
    }

    @XmlElement
    public double getRating() {
        return restaurant.getRating();
    }

    @XmlElement
    public List<CategoryElement> getCategories() {
        if (!elementConfig.withNestedObjects()) {
            return null;
        }
        return Converters.convertList(restaurant.getCategories(), new CategoryElementConverter(elementConfig));
    }
    
    @XmlElement
    public List<RestaurantCommentElement> getComments() {
        if (!elementConfig.withNestedObjects()) {
            return null;
        }
        List<RestaurantComment> sortedComments = RestaurantComments.getSortedComments(restaurant.getComments(), elementConfig.getPreferredLanguageCode());
        return Converters.convertList(sortedComments, new RestaurantCommentElementConverter(elementConfig));
    }
    
}

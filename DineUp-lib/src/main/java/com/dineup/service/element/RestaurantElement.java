package com.dineup.service.element;

import com.dineup.common.dom.Coordinate;
import com.dineup.common.dom.Coordinates;
import com.dineup.dom.Locale;
import com.dineup.service.ElementContext;
import com.dineup.dom.Restaurant;
import com.dineup.dom.Comments;
import com.dineup.dom.RestaurantLocale;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.converter.CategoryElementConverter;
import com.dineup.service.element.converter.CoordinateElementConverter;
import com.dineup.service.element.converter.CommentElementConverter;
import com.dineup.util.Converters;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.dineup.dom.Comment;

@XmlRootElement(name = "restaurant")
public class RestaurantElement {

    private ElementContext elementContext;
    private ElementConfig elementConfig;
    private Restaurant restaurant;

    public RestaurantElement() {
        // JAXB needs this
    }

    public RestaurantElement(ElementContext elementContext, ElementConfig elementConfig, Restaurant restaurant) {
        this.elementContext = elementContext;
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
    public String getLanguageCode() {
        Locale l = getLocale();
        return l == null ? null : l.getLanguageCode();
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
        return Converters.convert(restaurant.getCoordinate(), new CoordinateElementConverter(elementContext, elementConfig));
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
        return Converters.convertList(restaurant.getCategories(), new CategoryElementConverter(elementContext, elementConfig));
    }
    
    @XmlElement
    public List<CommentElement> getComments() {
        if (!elementConfig.withNestedObjects()) {
            return null;
        }
        List<Comment> sortedComments = Comments.getSortedComments(restaurant.getComments(), elementConfig.getPreferredLanguageCode());
        return Converters.convertList(sortedComments, new CommentElementConverter(elementContext, elementConfig));
    }
    
}

package com.dineup.ejb.soap;

import com.dineup.dom.Coordinate;
import com.dineup.service.ElementContext;
import com.dineup.dom.Restaurant;
import com.dineup.ejb.db.RestaurantDataSource;
import com.dineup.ejb.profile.ProfileManagerFactory;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.RestaurantElement;
import com.dineup.service.element.converter.RestaurantElementConverter;
import com.dineup.util.Converters;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Sample SOAP Web Service Code
 * WSDL: http://localhost:8080/DineUpService/Restaurant?wsdl
 * XSD:  http://localhost:8080/DineUpService/Restaurant?xsd=1
 */
@Singleton
@WebService(name = "Restaurant", serviceName = SoapKeys.SERVICE_NAME, targetNamespace = SoapKeys.NAMESPACE)
@LocalBean
public class RestaurantSoapResourceBean {
    
    @EJB
    private RestaurantDataSource dataSource;
    
    @EJB
    private ProfileManagerFactory profileManagerFactory;
    
    private ElementContext createElementContext() {
        return ElementContext.newBuilder()
                .profileManagerFactory(profileManagerFactory)
                .build();
    }
    
    @WebMethod
    public List<RestaurantElement> getRestaurants(String languageCode, Coordinate coordinate) {
        ElementConfig elementConfig = ElementConfig.newBuilder()
                .languageCode(languageCode)
                .coordinate(coordinate)
                .build();
        List<Restaurant> objects = dataSource.getRestaurants();
        List<RestaurantElement> elements = Converters.convertList(objects, new RestaurantElementConverter(createElementContext(), elementConfig));
        return elements;
    }
    
}

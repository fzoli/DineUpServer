package com.dineup.ejb;

import com.dineup.dom.Restaurant;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.RestaurantElement;
import com.dineup.rest.element.converter.RestaurantElementConverter;
import com.dineup.util.Converters;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Sample SOAP Web Service Code
 * WSDL: http://localhost:8080/DineUp/soap/Restaurant?wsdl
 * XSD: http://localhost:8080/DineUp/soap/Restaurant?xsd=1
 */
@Singleton
@WebService(name = "Restaurant", serviceName = SoapKeys.SERVICE_NAME, targetNamespace = SoapKeys.NAMESPACE)
@LocalBean
public class RestaurantSoapBean {
    
    @EJB
    private RestaurantDataSource dataSource;
    
    @WebMethod
    public List<RestaurantElement> getRestaurants(String languageCode) {
        ElementConfig elementConfig = ElementConfig.newBuilder().languageCode(languageCode).build();
        List<Restaurant> objects = dataSource.getRestaurants();
        List<RestaurantElement> elements = Converters.convertList(objects, new RestaurantElementConverter(elementConfig));
        return elements;
    }
    
}

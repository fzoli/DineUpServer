package com.dineup.ejb;

import com.dineup.rest.HeaderKeys;
import com.dineup.dom.Restaurant;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.RestaurantElement;
import javax.ws.rs.core.Response;
import com.dineup.rest.element.TestElement;
import com.dineup.rest.element.converter.RestaurantElementConverter;
import com.dineup.util.Converters;
import com.dineup.util.Exceptions;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.core.GenericEntity;

@Singleton
public class RestaurantRestResourceBean implements RestaurantRestResource, HeaderKeys {
    
    @EJB
    private RestaurantDataSource dataSource;
    
    @Override
    public Response getTestResponse(ElementConfig elementConfig) {
        if (!(elementConfig.getLanguageCode().equals("hu") || elementConfig.getLanguageCode().equals("en"))) {
            return Response.status(Response.Status.BAD_REQUEST)
                .header(ERROR_MESSAGE, "Unknown language code")
                .build();
        }
        String localizedText = elementConfig.getLanguageCode().equals("hu") ? "alma" : "apple";
        TestElement test = new TestElement(localizedText);
        GenericEntity<TestElement> entity = new GenericEntity<TestElement>(test) {};
        return Response.ok(entity)
                .build();
    }

    @Override
    public Response getRestaurants(ElementConfig elementConfig) {
        try {
            List<Restaurant> restaurants = dataSource.getRestaurants();
            List<RestaurantElement> restaurantElements = Converters.convertList(restaurants, new RestaurantElementConverter(elementConfig));
            GenericEntity<List<RestaurantElement>> entity = new GenericEntity<List<RestaurantElement>>(restaurantElements) {};
            return Response.ok(entity)
                    .build();
        }
        catch (Exception ex) {
            return Response.serverError()
                    .header(ERROR_MESSAGE, Exceptions.getRootCause(ex).getMessage())
                    .build();
        }
    }
    
}

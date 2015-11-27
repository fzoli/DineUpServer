package com.dineup.servlet;

import com.dineup.dom.Restaurant;
import com.dineup.ejb.RestaurantDataSource;
import com.dineup.gson.GsonFactory;
import com.dineup.gson.SerializerConfig;
import com.dineup.response.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RestaurantJsonServlet", urlPatterns = {"/Restaurants"})
public class RestaurantJsonServlet extends HttpServlet {

    @EJB
    private RestaurantDataSource dataSource;

    private final ResponseMessageFactory messageFactory = new ResponseMessageFactory();

    @Override
    public String getServletInfo() {
        return "Prints the list of restaurants in JSON format";
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SerializerConfig config = SerializerConfigFactory.createInstance(request);
        Gson gson = GsonFactory.createInstance(config);
        response.setContentType(ContentTypes.JSON);
        try (PrintWriter out = response.getWriter()) {
            out.println(gson.toJson(createRestaurantListResponseMessage()));
        }
    }

    private ResponseMessage<List<Restaurant>> createRestaurantListResponseMessage() {
        return messageFactory.createMessage(new RestaurantListResponseGenerator(dataSource));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}

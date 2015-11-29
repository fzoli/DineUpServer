package com.dineup.servlet;

import com.dineup.dom.Category;
import com.dineup.ejb.RestaurantDataSource;
import com.dineup.gson.GsonFactory;
import com.dineup.gson.SerializerConfig;
import com.dineup.response.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoryJsonServlet", urlPatterns = {"/Categories"})
public class CategoryJsonServlet extends HttpServlet {

    @EJB
    private RestaurantDataSource dataSource;

    private final ResponseMessageFactory messageFactory = new ResponseMessageFactory(new ErrorResolver() {
        @Override
        public ErrorBundle resolveError(Exception exception) {
            if (exception instanceof NumberFormatException) {
                return new ErrorBundle(Result.INVALID_REQUEST, exception);
            }
            return null;
        }
    });

    @Override
    public String getServletInfo() {
        return "Prints the list of categories in JSON format";
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SerializerConfig config = SerializerConfigFactory.createInstance(request);
        Gson gson = GsonFactory.createInstance(config);
        JsonMessageStreamer.writeJson(gson, response, createCategoryListResponseMessage(request.getParameter("restaurantId")));
    }

    private ResponseMessage<List<Category>> createCategoryListResponseMessage(final String restaurantIdText) {
        return messageFactory.createMessage(new CategoryListResponseGenerator(dataSource, new CategoryListResponseGenerator.Query() {
            @Override
            public int getRestaurantId() {
                return Integer.parseInt(restaurantIdText);
            }
        }));
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

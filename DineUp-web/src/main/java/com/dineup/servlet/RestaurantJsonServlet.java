package com.dineup.servlet;

import com.dineup.ejb.RestaurantDataSource;
import com.dineup.gson.GsonFactory;
import com.dineup.dom.Localization;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String languageCode = request.getParameter("language");
        if (languageCode == null) languageCode = "hu";
        String currencyCode = request.getParameter("currency");
        if (currencyCode == null) currencyCode = "HUF";
        Gson gson = GsonFactory.createInstance(new Localization(languageCode, currencyCode));
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(gson.toJson(dataSource.getRestaurants()));
        }
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

    @Override
    public String getServletInfo() {
        return "Prints the list of restaurants in JSON format";
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.model.RoomForRentDao;
import pe.model.RoomForRentDto;

/**
 *
 * @author uydat
 */
@WebServlet(name = "ActiveListingsController", urlPatterns = {"/active-listings"})
public class ActiveListingsController extends HttpServlet {

    private String validateNumericParameter(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            Double.parseDouble(value);
            return value;
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    private String validateSortOrder(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        if ("ASC".equals(value) || "DESC".equals(value)) {
            return value;
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect("login");
            return;
        }
        
        try {
            String minPriceStr = request.getParameter("minPrice");
            String maxPriceStr = request.getParameter("maxPrice");
            String sortOrder = request.getParameter("sortOrder");
            
            Double minPrice = null;
            Double maxPrice = null;
            
            try {
                if (minPriceStr != null && !minPriceStr.trim().isEmpty()) {
                    minPrice = Double.parseDouble(minPriceStr);
                }
                if (maxPriceStr != null && !maxPriceStr.trim().isEmpty()) {
                    maxPrice = Double.parseDouble(maxPriceStr);
                }
            } catch (NumberFormatException e) {
                // Invalid number format, ignore and use null
            }
            
            RoomForRentDao dao = new RoomForRentDao();
            List<RoomForRentDto> listings = dao.getActiveListings(minPrice, maxPrice, sortOrder);
            
            request.setAttribute("listings", listings);
            request.setAttribute("minPrice", minPriceStr);
            request.setAttribute("maxPrice", maxPriceStr);
            request.setAttribute("sortOrder", sortOrder);
            
            request.getRequestDispatcher("active_listings.jsp").forward(request, response);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActiveListingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect("login");
            return;
        }
        
        try {
            String action = request.getParameter("action");
            
            if ("expire".equals(action)) {
                try {
                    int roomId = Integer.parseInt(request.getParameter("roomId"));
                    RoomForRentDao dao = new RoomForRentDao();
                    dao.updateRoomStatus(roomId, 2);
                } catch (NumberFormatException e) {
                    // Invalid room ID, ignore
                }
                
                // Validate and sanitize parameters to prevent open redirect
                String minPrice = validateNumericParameter(request.getParameter("minPrice"));
                String maxPrice = validateNumericParameter(request.getParameter("maxPrice"));
                String sortOrder = validateSortOrder(request.getParameter("sortOrder"));
                
                try {
                    StringBuilder redirectUrl = new StringBuilder("active-listings");
                    boolean hasParams = false;
                    
                    if (minPrice != null) {
                        redirectUrl.append(hasParams ? "&" : "?");
                        redirectUrl.append("minPrice=").append(URLEncoder.encode(minPrice, StandardCharsets.UTF_8.toString()));
                        hasParams = true;
                    }
                    if (maxPrice != null) {
                        redirectUrl.append(hasParams ? "&" : "?");
                        redirectUrl.append("maxPrice=").append(URLEncoder.encode(maxPrice, StandardCharsets.UTF_8.toString()));
                        hasParams = true;
                    }
                    if (sortOrder != null) {
                        redirectUrl.append(hasParams ? "&" : "?");
                        redirectUrl.append("sortOrder=").append(URLEncoder.encode(sortOrder, StandardCharsets.UTF_8.toString()));
                        hasParams = true;
                    }
                    
                    response.sendRedirect(redirectUrl.toString());
                } catch (Exception e) {
                    // Encoding error, redirect to base page
                    response.sendRedirect("active-listings");
                }
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActiveListingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Active Listings Controller";
    }
}

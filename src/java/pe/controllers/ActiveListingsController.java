/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
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
            
            if (minPriceStr != null && !minPriceStr.trim().isEmpty()) {
                minPrice = Double.parseDouble(minPriceStr);
            }
            if (maxPriceStr != null && !maxPriceStr.trim().isEmpty()) {
                maxPrice = Double.parseDouble(maxPriceStr);
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
                int roomId = Integer.parseInt(request.getParameter("roomId"));
                RoomForRentDao dao = new RoomForRentDao();
                dao.updateRoomStatus(roomId, 2);
                
                String minPrice = request.getParameter("minPrice");
                String maxPrice = request.getParameter("maxPrice");
                String sortOrder = request.getParameter("sortOrder");
                
                StringBuilder redirectUrl = new StringBuilder("active-listings?");
                if (minPrice != null && !minPrice.isEmpty()) {
                    redirectUrl.append("minPrice=").append(minPrice).append("&");
                }
                if (maxPrice != null && !maxPrice.isEmpty()) {
                    redirectUrl.append("maxPrice=").append(maxPrice).append("&");
                }
                if (sortOrder != null && !sortOrder.isEmpty()) {
                    redirectUrl.append("sortOrder=").append(sortOrder);
                }
                
                response.sendRedirect(redirectUrl.toString());
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

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//
//package pe.controllers;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import pe.utils.DbUtils;
//
///**
// *
// * @author uydat
// */
//@WebServlet(name="ActiveController", urlPatterns={"/ActiveController"})
//public class ActiveController extends HttpServlet {
//    private static final String LOGIN_PAGE = "login.jsp";
//    private static final String ACTIVE_LISTINGS_PAGE = "active_listings.jsp";
//   
//    /** 
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        String url = LOGIN_PAGE;
//        try {
//            // Check if user is authenticated
//            HttpSession session = request.getSession(false);
//            if (session == null || session.getAttribute("LOGIN_USER") == null) {
//                // Redirect to login if not authenticated (requirement 2.1)
//                url = LOGIN_PAGE;
//            } else {
//                // Get filter parameters
//                String minPriceStr = request.getParameter("minPrice");
//                String maxPriceStr = request.getParameter("maxPrice");
//                String sortOrder = request.getParameter("sortOrder"); // "asc" or "desc"
//                String expiredId = request.getParameter("expiredId");
//                
//                // Handle expired button click
//                if (expiredId != null && !expiredId.isEmpty()) {
//                    DbUtils.expireRoomListing(Integer.parseInt(expiredId));
//                }
//                
//                // Get room listings with filters
//                Double minPrice = null;
//                Double maxPrice = null;
//                
//                if (minPriceStr != null && !minPriceStr.trim().isEmpty()) {
//                    try {
//                        minPrice = Double.parseDouble(minPriceStr.trim());
//                    } catch (NumberFormatException e) {
//                        // Invalid price format, ignore
//                    }
//                }
//                
//                if (maxPriceStr != null && !maxPriceStr.trim().isEmpty()) {
//                    try {
//                        maxPrice = Double.parseDouble(maxPriceStr.trim());
//                    } catch (NumberFormatException e) {
//                        // Invalid price format, ignore
//                    }
//                }
//                
//                List<RoomListing> listings = DBUtils.getActiveRoomListings(minPrice, maxPrice, sortOrder);
//                
//                // Set attributes for JSP
//                request.setAttribute("ROOM_LISTINGS", listings);
//                request.setAttribute("minPrice", minPriceStr);
//                request.setAttribute("maxPrice", maxPriceStr);
//                request.setAttribute("sortOrder", sortOrder);
//                
//                url = ACTIVE_LISTINGS_PAGE;
//            }
//        } catch (Exception e) {
//            log("Error at ActiveListingsController: " + e.toString());
//            e.printStackTrace();
//        } finally {
//            request.getRequestDispatcher(url).forward(request, response);
//        }
//    } 
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /** 
//     * Handles the HTTP <code>GET</code> method.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        processRequest(request, response);
//    } 
//
//    /** 
//     * Handles the HTTP <code>POST</code> method.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /** 
//     * Returns a short description of the servlet.
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}

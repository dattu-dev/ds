/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.utils.DbUtils;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class RoomForRentDao {
    //-----            your code here   --------------------------------
    
    public List<RoomForRentDto> getActiveListings(Double minPrice, Double maxPrice, String sortOrder) throws ClassNotFoundException {
        List<RoomForRentDto> listings = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT r.*, a.username FROM RoomForRent r JOIN Account a ON r.username = a.username WHERE r.status = 0");
        
        if (minPrice != null) {
            sql.append(" AND r.price >= ?");
        }
        if (maxPrice != null) {
            sql.append(" AND r.price <= ?");
        }
        
        if (sortOrder != null && (sortOrder.equals("ASC") || sortOrder.equals("DESC"))) {
            sql.append(" ORDER BY r.price ").append(sortOrder);
        }
        
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            
            int paramIndex = 1;
            if (minPrice != null) {
                ps.setDouble(paramIndex++, minPrice);
            }
            if (maxPrice != null) {
                ps.setDouble(paramIndex++, maxPrice);
            }
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                RoomForRentDto room = new RoomForRentDto();
                room.setId(rs.getInt("id"));
                room.setTitle(rs.getString("title"));
                room.setPrice(rs.getDouble("price"));
                room.setLocation(rs.getString("location"));
                room.setDescription(rs.getString("description"));
                room.setPostedDate(rs.getDate("postedDate"));
                room.setStatus(rs.getInt("status"));
                room.setUsername(rs.getString("username"));
                listings.add(room);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listings;
    }
    
    public boolean updateRoomStatus(int roomId, int status) throws ClassNotFoundException {
        String sql = "UPDATE RoomForRent SET status = ? WHERE id = ?";
        
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, status);
            ps.setInt(2, roomId);
            
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

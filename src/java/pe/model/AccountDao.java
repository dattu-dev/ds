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
import pe.utils.DbUtils;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class AccountDao {

    //-----            your code here   --------------------------------
    public AccountDto login(String username, String password) throws ClassNotFoundException {
        AccountDto account = null;
        String sql = "SELECT * FROM Account WHERE username = ? AND password = ? AND status = 1";

        try ( Connection conn = DbUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                account = new AccountDto();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setFullName(rs.getString("fullName"));
                account.setPhone(rs.getString("phone"));
                account.setEmail(rs.getString("email"));
                account.setStatus(rs.getBoolean("status"));
                account.setRole(rs.getInt("role"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }
}

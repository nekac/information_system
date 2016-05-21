/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBeans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DB;

/**
 *
 * @author Nikola
 */
public class User {

    private String username;
    private String password;
    private int type;
    private int valid;

    public User() {}
    
    public User(String username, String password, int type, int valid) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.valid = valid;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public static User getUser(String username) {
        String query = "select * from user where username = '" + username + "'";
        ResultSet rs;
        User user = new User();
        rs = DB.executeQuery(query);
        try {
            if (rs.next()) {
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setType(rs.getInt("type"));
                user.setValid(rs.getInt("valid"));
                return user;
            } else {
                user.setUsername(" ");
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void updateUser(String uusername, String ppassword) {
        DB.executeUpdate("update user set password = '" + ppassword + "' where username = '" + uusername + "'");
    }
    
    public static ResultSet getUserValid() {
        ResultSet rs;
        rs = DB.executeQuery("select * from user where valid = '0'");
        return rs;
    }
    
    public static void setUserValid(String uusername) {
        DB.executeUpdate("update user set valid = 1 where username = '" + uusername + "'");
    }
    
    public static void odbaciUsera(String uusername) {
        DB.executeUpdate("update user set valid = 2 where username = '" + uusername + "'");
    }
    
    
}

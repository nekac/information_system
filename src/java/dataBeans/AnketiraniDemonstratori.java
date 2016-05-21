/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBeans;

import beans.LogInBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DB;

/**
 *
 * @author Nikola
 */
public class AnketiraniDemonstratori {
    
    private String username;
    private int anketiran;
    
    public AnketiraniDemonstratori() {}

    public String getUsername() {
        return username;
    }

    public int getAnketiran() {
        return anketiran;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAnketiran(int anketiran) {
        this.anketiran = anketiran;
    }
    
    public static void addAnketiraniDemos(int value) {
        DB.executeUpdate("insert into anketiranidemonstratori (username, anketiran) values ('" + LogInBean.user.getUsername() + "', '" + value + "')");
    }
    
    public static int ucitajAnketiraniDemos() {
        int n = 0;
        ResultSet rs;
        rs = DB.executeQuery("select * from anketiranidemonstratori where username = '" + LogInBean.user.getUsername() + "'");
        try {
            while (rs.next()) {
                String u = rs.getString("username");
                n = rs.getInt("anketiran");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnketiraniDemonstratori.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public static void isprazniAnketirane() {
        DB.executeUpdate("delete from anketiraniDemonstratori");
    }
    
}

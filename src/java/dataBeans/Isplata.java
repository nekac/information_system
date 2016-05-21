/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBeans;

import beans.LogInBean;
import java.sql.ResultSet;
import java.util.Date;
import utilities.DB;

/**
 *
 * @author Nikola
 */
public class Isplata {
    
    private String username;
    private String tip;
    private java.util.Date datum;
    private double isplata;
    
    public Isplata() {}

    public String getUsername() {
        return username;
    }

    public String getTip() {
        return tip;
    }

    public Date getDatum() {
        return datum;
    }

    public double getIsplata() {
        return isplata;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setIsplata(double isplata) {
        this.isplata = isplata;
    }
    
    public static void addIsplata(String uusername, String ttip, java.sql.Date ddatum, double iisplata) {
        DB.executeUpdate("insert into isplate (username, tip, datum, isplata) values ('" + uusername + "', '" + ttip + "', '" + ddatum + "', '" + iisplata + "')");
    }
    
    public static ResultSet citajIsplate() {
        ResultSet rs;
        rs = DB.executeQuery("select * from isplate where username = '" + LogInBean.user.getUsername() + "'");
        return rs;
    }
    
    
}

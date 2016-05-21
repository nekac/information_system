/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBeans;

import beans.LogInBean;
import java.sql.ResultSet;
import utilities.DB;

/**
 *
 * @author Nikola
 */
public class NastavnikPredmet {

    private String username;
    private String predmet;
    
    public NastavnikPredmet() {}

    public NastavnikPredmet(String username, String predmet) {
        this.username = username;
        this.predmet = predmet;
    }

    public String getUsername() {
        return username;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }
    
    public static ResultSet ucitajNastavnikPredmet() {
        ResultSet rs;
        rs = DB.executeQuery("select * from nastavnikpredmet where username = '" + LogInBean.user.getUsername() + "'");
        return rs;
    }
    
    public static void addNastavnikPredmet(String us, String sif) {
        DB.executeUpdate("insert into nastavnikpredmet (username, predmet) values ('" + us + "', '" + sif + "')");
    }
    
    
}

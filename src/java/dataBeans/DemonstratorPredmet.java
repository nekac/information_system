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
public class DemonstratorPredmet {
    
    private String username;
    private String predmet;
    
    public DemonstratorPredmet() {}

    public DemonstratorPredmet(String username, String predmet) {
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
    
    public static void addDemonstratorPredmet(String username, String predmet) {
        DB.executeUpdate("insert into demonstratorpredmet (username, predmet) values ('" + username + "', '" + predmet + "')");
    }
    
    public static ResultSet citajDemonstratorPredmet() {
        ResultSet rs;
        rs = DB.executeQuery("select * from demonstratorpredmet where username = '" + LogInBean.user.getUsername() + "'");
        return rs;
    }
    
    public static void isprazniDemPred() {
        DB.executeUpdate("delete from demonstratorpredmet");
    }
}

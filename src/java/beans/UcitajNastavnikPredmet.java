/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.NastavnikPredmet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Nikola
 */
@ManagedBean (name="ucitajNastavnikPredmet")
@RequestScoped
public class UcitajNastavnikPredmet {
    
    private LinkedList<NastavnikPredmet> vezaNastavnikPredmet;
    
    private String username;
    private String predmet;
    
    public UcitajNastavnikPredmet() {
        vezaNastavnikPredmet = ucitajVezaNastavnikPredmet();
    }

    public LinkedList<NastavnikPredmet> getVezaNastavnikPredmet() {
        return vezaNastavnikPredmet;
    }

    public String getUsername() {
        return username;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setVezaNastavnikPredmet(LinkedList<NastavnikPredmet> vezaNastavnikPredmet) {
        this.vezaNastavnikPredmet = vezaNastavnikPredmet;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }
    
    public static LinkedList<NastavnikPredmet> ucitajVezaNastavnikPredmet() {
        ResultSet rs = NastavnikPredmet.ucitajNastavnikPredmet();
        LinkedList<NastavnikPredmet> veze = new LinkedList<NastavnikPredmet>();
        try {
            while (rs.next()) {
                NastavnikPredmet np = new NastavnikPredmet();
                np.setUsername(rs.getString("username"));
                np.setPredmet(rs.getString("predmet"));
                veze.add(np);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PretragaNastavnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return veze;
    }
    
}

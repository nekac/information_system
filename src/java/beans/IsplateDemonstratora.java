/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.Isplata;
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
@ManagedBean (name="isplateDemonstratora")
@RequestScoped
public class IsplateDemonstratora {
    
    private LinkedList<Isplata> mojeIsplate;
    private Double ukupnoIsplaceno;
    
    public IsplateDemonstratora() {
        mojeIsplate = ucitajIsplate();
        ukupnoIsplaceno = isplata();
    }

    public LinkedList<Isplata> getMojeIsplate() {
        return mojeIsplate;
    }

    public void setMojeIsplate(LinkedList<Isplata> mojeIsplate) {
        this.mojeIsplate = mojeIsplate;
    }

    public Double getUkupnoIsplaceno() {
        return ukupnoIsplaceno;
    }

    public void setUkupnoIsplaceno(Double ukupnoIsplaceno) {
        this.ukupnoIsplaceno = ukupnoIsplaceno;
    }
    
    private LinkedList<Isplata> ucitajIsplate() {
        LinkedList<Isplata> isplate = new LinkedList<Isplata>();
        ResultSet rs = Isplata.citajIsplate();
        try {
            while (rs.next()) {
                Isplata i = new Isplata();
                i.setUsername(rs.getString("username"));
                i.setTip(rs.getString("tip"));
                i.setDatum(rs.getDate("datum"));
                i.setIsplata(rs.getDouble("isplata"));
                isplate.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IsplateDemonstratora.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isplate;
    }
    
    private double isplata() {
        double ukupno = 0;
        for (Isplata i : mojeIsplate) {
            ukupno += i.getIsplata();
        }
        return ukupno;
    }
    
}

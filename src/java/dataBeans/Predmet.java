/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBeans;

import java.sql.ResultSet;
import javax.faces.bean.ManagedBean;
import utilities.DB;

/**
 *
 * @author Nikola
 */
@ManagedBean
public class Predmet {
    
    private String sifra;
    private String naziv;
    private String semestar;
    private String skolaskaGodina;
    private boolean check = false;
    
    public Predmet() {}

    public Predmet(String sifra, String naziv, String semestar, String skolaskaGodina) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.semestar = semestar;
        this.skolaskaGodina = skolaskaGodina;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheck() {
        return check;
    }

    public String getSifra() {
        return sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getSemestar() {
        return semestar;
    }

    public String getSkolaskaGodina() {
        return skolaskaGodina;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setSemestar(String semestar) {
        this.semestar = semestar;
    }

    public void setSkolaskaGodina(String skolaskaGodina) {
        this.skolaskaGodina = skolaskaGodina;
    }
    
    public static ResultSet citajPredmete() {
        ResultSet rs;
        rs = DB.executeQuery("select * from predmet");
        return rs;
    }
    
    public static ResultSet citajTekucePredmete(String skolskaGodina) {
        ResultSet rs;
        rs = DB.executeQuery("select * from predmet where skolska_godina = '" + skolskaGodina + "'");
        // if empty query remove table from interface
        return rs;
    }
    
    public static ResultSet citajPredmeteSaSifrom(String sifra) {
        ResultSet rs;
        rs = DB.executeQuery("select * from predmet where sifra = '" + sifra + "'");
        return rs;
    }
    
    public static void addPredmet(String sifra, String naziv, String semestar, String skolska_godina) {
        DB.executeUpdate("insert into predmet (sifra, naziv, semestar, skolska_godina) values ('" + sifra + "', '" + naziv + "', '" + semestar + "', '" + skolska_godina + "')");
    }
    
}

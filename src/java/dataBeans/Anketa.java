/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBeans;

import java.sql.ResultSet;
import utilities.DB;

/**
 *
 * @author Nikola
 */
public class Anketa {
    
    private String sifra;
    private String naziv;
    private String semestar;
    private String skolaskaGodina;
    
    public Anketa() {}

    public Anketa(String sifra, String naziv, String semestar, String skolaskaGodina) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.semestar = semestar;
        this.skolaskaGodina = skolaskaGodina;
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
    
    public static ResultSet citajAnketu() {
        ResultSet rs;
        rs = DB.executeQuery("select * from anketa");
        return rs;
    }
    
    public static void addPredmet(String sifra, String naziv, String semestar, String skolska_godina) {
        DB.executeUpdate("insert into anketa (sifra, naziv, semestar, skolska_godina) values ('" + sifra + "', '" + naziv + "', '" + semestar + "', '" + skolska_godina + "')");
    }
    
    public static void isprazniAnketu() {
        DB.executeUpdate("delete from anketa");
    }
    
}

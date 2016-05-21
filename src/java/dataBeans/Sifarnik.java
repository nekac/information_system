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
public class Sifarnik {
    
    private int sifra;
    private String nazivAktivnosti;
    private double koeficijent;
    
    public Sifarnik() {}

    public Sifarnik(int sifra, String nazivAktivnosti, double koeficijent) {
        this.sifra = sifra;
        this.nazivAktivnosti = nazivAktivnosti;
        this.koeficijent = koeficijent;
    }

    public int getSifra() {
        return sifra;
    }

    public String getNazivAktivnosti() {
        return nazivAktivnosti;
    }

    public double getKoeficijent() {
        return koeficijent;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public void setNazivAktivnosti(String nazivAktivnosti) {
        this.nazivAktivnosti = nazivAktivnosti;
    }

    public void setKoeficijent(double koeficijent) {
        this.koeficijent = koeficijent;
    }
    
    public static ResultSet ucitajSifre() {
        ResultSet rs = DB.executeQuery("select * from sifarnik");
        return rs;
    }
    
}

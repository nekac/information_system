/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBeans;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Nikola
 */
public class Obracun {
    
    private String username;
    private int idLab;
    private String ime;
    private String prezime;
    private String odsek;
    private int godina;
    private java.sql.Date datum;
    private Time vremeOd;
    private Time vremeDo;
    private double suma;
    
    public Obracun() {}

    public void setIdLab(int idLab) {
        this.idLab = idLab;
    }

    public int getIdLab() {
        return idLab;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getOdsek() {
        return odsek;
    }

    public int getGodina() {
        return godina;
    }

    public Date getDatum() {
        return datum;
    }

    public Time getVremeOd() {
        return vremeOd;
    }

    public Time getVremeDo() {
        return vremeDo;
    }

    public double getSuma() {
        return suma;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setOdsek(String odsek) {
        this.odsek = odsek;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setVremeOd(Time vremeOd) {
        this.vremeOd = vremeOd;
    }

    public void setVremeDo(Time vremeDo) {
        this.vremeDo = vremeDo;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }
    
    public double izracunajSumu() {
        long ukupnoVreme = vremeDo.getTime() - vremeOd.getTime();
        double minutes = (double)(ukupnoVreme/(1000*60));
        minutes = (minutes/45)*LabVezba.CENA;
        return minutes;
    }
    
}

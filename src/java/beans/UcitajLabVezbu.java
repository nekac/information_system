/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.LabVezba;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Nikola
 */
@ManagedBean (name="ucitajLabVezbu")
@RequestScoped
public class UcitajLabVezbu {
    
    private String predmet;
    private String naziv;
    private Date datum;
    private Time vremeod;
    private Time vremedo;
    private String laboratorija;
    private int tipaktivnosti;
    private int maxbrojstudenata;
    private int trenutnoprijavljenih;
    private String username;
    private int kljuc;
    
    private LinkedList<LabVezba> mojeLabVezbe;
    private LinkedList<LabVezba> mojeNepotvrdjeneLabVezbe;
    private LinkedList<LabVezba> neobracunateLabVezbe;
    
    public UcitajLabVezbu() {
        mojeLabVezbe = ucitajLabVezbe();
        mojeNepotvrdjeneLabVezbe = ucitajNepotvrdjeneLabVezbe();
        neobracunateLabVezbe = ucitajNeobracunateLabVezbe();
    }

    public LinkedList<LabVezba> getNeobracunateLabVezbe() {
        return neobracunateLabVezbe;
    }

    public void setNeobracunateLabVezbe(LinkedList<LabVezba> neobracunateLabVezbe) {
        this.neobracunateLabVezbe = neobracunateLabVezbe;
    }
    
    public LinkedList<LabVezba> getMojeNepotvrdjeneLabVezbe() {
        return mojeNepotvrdjeneLabVezbe;
    }

    public void setMojeNepotvrdjeneLabVezbe(LinkedList<LabVezba> mojeNepotvrdjeneLabVezbe) {
        this.mojeNepotvrdjeneLabVezbe = mojeNepotvrdjeneLabVezbe;
    }
    
    public int getTrenutnoprijavljenih() {
        return trenutnoprijavljenih;
    }

    public void setTrenutnoprijavljenih(int trenutnoprijavljenih) {
        this.trenutnoprijavljenih = trenutnoprijavljenih;
    }

    public LinkedList<LabVezba> getMojeLabVezbe() {
        return mojeLabVezbe;
    }

    public void setMojeLabVezbe(LinkedList<LabVezba> mojeLabVezbe) {
        this.mojeLabVezbe = mojeLabVezbe;
    }
    
    public String getPredmet() {
        return predmet;
    }

    public String getNaziv() {
        return naziv;
    }

    public Date getDatum() {
        return datum;
    }

    public Time getVremeod() {
        return vremeod;
    }

    public Time getVremedo() {
        return vremedo;
    }

    public String getLaboratorija() {
        return laboratorija;
    }

    public int getTipaktivnosti() {
        return tipaktivnosti;
    }

    public int getMaxbrojstudenata() {
        return maxbrojstudenata;
    }

    public String getUsername() {
        return username;
    }

    public int getKljuc() {
        return kljuc;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setVremeod(Time vremeod) {
        this.vremeod = vremeod;
    }

    public void setVremedo(Time vremedo) {
        this.vremedo = vremedo;
    }

    public void setLaboratorija(String laboratorija) {
        this.laboratorija = laboratorija;
    }

    public void setTipaktivnosti(int tipaktivnosti) {
        this.tipaktivnosti = tipaktivnosti;
    }

    public void setMaxbrojstudenata(int maxbrojstudenata) {
        this.maxbrojstudenata = maxbrojstudenata;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setKljuc(int kljuc) {
        this.kljuc = kljuc;
    }

    private LinkedList<LabVezba> ucitajLabVezbe() {
        LinkedList<LabVezba> vezbe = new LinkedList<LabVezba>();
        ResultSet rs = LabVezba.ucitajSveMojeLabVezbe();
        try {
            while (rs.next()) {
                LabVezba lab = new LabVezba();
                lab.setIdLabVezbe(rs.getInt("idLabVezbe"));
                lab.setPredmet(rs.getString("predmet"));
                lab.setNaziv(rs.getString("naziv"));
                lab.setDatum(rs.getDate("datum"));
                lab.setVremeod(rs.getTime("vremeod"));
                lab.setVremedo(rs.getTime("vremedo"));
                lab.setLaboratorija(rs.getString("laboratorija"));
                lab.setTipaktivnosti(rs.getInt("tipaktivnosti"));
                lab.setMaxbrojstudenata(rs.getInt("maxbrojstudenata"));
                lab.setTrenutnoprijavljeno(rs.getInt("trenutnoprijavljeno"));
                lab.setUsername(rs.getString("username"));
                lab.setKljuc(rs.getInt("kljuc"));
                if (lab.getKljuc() != 0) {
                    vezbe.add(lab);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UcitajLabVezbu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vezbe;
    }
    
    private LinkedList<LabVezba> ucitajNepotvrdjeneLabVezbe() {
        LinkedList<LabVezba> vezbe = new LinkedList<LabVezba>();
        ResultSet rs = LabVezba.ucitajSveMojeLabVezbe();
        try {
            while (rs.next()) {
                LabVezba lab = new LabVezba();
                lab.setIdLabVezbe(rs.getInt("idLabVezbe"));
                lab.setPredmet(rs.getString("predmet"));
                lab.setNaziv(rs.getString("naziv"));
                lab.setDatum(rs.getDate("datum"));
                lab.setVremeod(rs.getTime("vremeod"));
                lab.setVremedo(rs.getTime("vremedo"));
                lab.setLaboratorija(rs.getString("laboratorija"));
                lab.setTipaktivnosti(rs.getInt("tipaktivnosti"));
                lab.setMaxbrojstudenata(rs.getInt("maxbrojstudenata"));
                lab.setTrenutnoprijavljeno(rs.getInt("trenutnoprijavljeno"));
                lab.setUsername(rs.getString("username"));
                lab.setKljuc(rs.getInt("kljuc"));
                if (lab.getKljuc() == 0) {
                    vezbe.add(lab);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UcitajLabVezbu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vezbe;
    }
    
    private LinkedList<LabVezba> ucitajNeobracunateLabVezbe() {
        LinkedList<LabVezba> vezbe = new LinkedList<LabVezba>();
        ResultSet rs = LabVezba.ucitajSveLabVezbe();
        try {
            while (rs.next()) {
                LabVezba lab = new LabVezba();
                lab.setIdLabVezbe(rs.getInt("idLabVezbe"));
                lab.setPredmet(rs.getString("predmet"));
                lab.setNaziv(rs.getString("naziv"));
                lab.setDatum(rs.getDate("datum"));
                lab.setVremeod(rs.getTime("vremeod"));
                lab.setVremedo(rs.getTime("vremedo"));
                lab.setLaboratorija(rs.getString("laboratorija"));
                lab.setTipaktivnosti(rs.getInt("tipaktivnosti"));
                lab.setMaxbrojstudenata(rs.getInt("maxbrojstudenata"));
                lab.setTrenutnoprijavljeno(rs.getInt("trenutnoprijavljeno"));
                lab.setUsername(rs.getString("username"));
                lab.setKljuc(rs.getInt("kljuc"));
                if (lab.getKljuc() == 1) {
                    vezbe.add(lab);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UcitajLabVezbu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vezbe;
    }
    
    public String obracunaj(LabVezba labb) {
        LabVezba.obracunajLabVezbu(labb.getIdLabVezbe());
        this.neobracunateLabVezbe.remove(labb);
        return null;
    }
    
}

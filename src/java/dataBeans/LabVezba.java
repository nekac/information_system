/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBeans;

import beans.LogInBean;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import utilities.DB;

/**
 *
 * @author Nikola
 */
@ManagedBean
public class LabVezba {
    
    public static final double CENA = 1000.00;
    
    private int idLabVezbe;
    private String predmet;
    private String naziv;
    private Date datum;
    private Time vremeod;
    private Time vremedo;
    private String laboratorija;
    private int tipaktivnosti;
    private int maxbrojstudenata;
    private int trenutnoprijavljeno;
    private String username;
    private int kljuc;
    private boolean edit = false;
    private String komentar = "";
    
    public LabVezba() {}

    public LabVezba(int idLabVezbe, String predmet, String naziv, Date datum, Time vremeod, Time vremedo, String laboratorija, int tipaktivnosti, int maxbrojstudenata, int trenutnoprijavljeno, String username, int kljuc) {
        this.idLabVezbe = idLabVezbe;
        this.predmet = predmet;
        this.naziv = naziv;
        this.datum = datum;
        this.vremeod = vremeod;
        this.vremedo = vremedo;
        this.laboratorija = laboratorija;
        this.tipaktivnosti = tipaktivnosti;
        this.maxbrojstudenata = maxbrojstudenata;
        this.trenutnoprijavljeno = trenutnoprijavljeno;
        this.username = username;
        this.kljuc = kljuc;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getKomentar() {
        return komentar;
    }

    public static double getCENA() {
        return CENA;
    }

    public void setIdLabVezbe(int idLabVezbe) {
        this.idLabVezbe = idLabVezbe;
    }

    public int getIdLabVezbe() {
        return idLabVezbe;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
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

    public int getTrenutnoprijavljeno() {
        return trenutnoprijavljeno;
    }

    public void setTrenutnoprijavljeno(int trenutnoprijavljeno) {
        this.trenutnoprijavljeno = trenutnoprijavljeno;
    }

    public static int addLabVezba(String predmet, String naziv, java.sql.Date datum, Time vremeod, Time vremedo, String laboratorija, 
            int tipaktivnosti, int maxbrojstudenata, int trenutnoprijavljeno, String username, int kljuc) {
        int br = DB.executeUpdate("insert into labvezba (predmet, naziv, datum, vremeod, vremedo, laboratorija, tipaktivnosti, "
                + "maxbrojstudenata, trenutnoprijavljeno, username, kljuc) values ('" + predmet + "', '" + naziv + "', '" + datum  + 
                "', '" + vremeod  + "', '" + vremedo + "', '" + laboratorija + "', '" + tipaktivnosti + "', '" + maxbrojstudenata  + 
                "', '" + trenutnoprijavljeno + "', '" + username + "', '" + kljuc + "')");
        return br;
    }
    
    public static ResultSet ucitajLabVezbu(String naziv) {
        ResultSet rs;
        rs = DB.executeQuery("select * from labvezba where naziv = '" + naziv + "'");
        return rs;
    }
    
    public static ResultSet ucitajSveMojeLabVezbe() {
        ResultSet rs;
        rs = DB.executeQuery("select * from labvezba where username = '" + LogInBean.user.getUsername() + "'");
        return rs;    
    }
    
    public static ResultSet ucitajSveMojeLabVezbeDemos(String sifra, String username) {
        ResultSet rs;
        rs = DB.executeQuery("select * from labvezba where predmet = '" + sifra + "' and username = '" + username + "'");
        return rs;    
    }
    
    public static ResultSet ucitajSveLabVezbe() {
        ResultSet rs;
        rs = DB.executeQuery("select * from labvezba where kljuc = 1");
        return rs;    
    }
    
    public static void updatelabVezba(int kljuc) {
        DB.executeUpdate("update labvezba set kljuc = '" + kljuc + "' where username = '" + LogInBean.user.getUsername() + "'");
    }
    
    public static void updatelabVezbaAdmin(int klj, int id) {
        DB.executeUpdate("update labvezba set kljuc = '" + klj + "' where idLabVezbe = '" + id + "'");
    }
    
    public String editTrue() {
        this.edit = true;
        return null;
    }
    
    public String editFalse(LabVezba labb) {
        sacuvajIzmeneVezbe(labb);
        this.edit = false;
        return null;
    }
    
    public static void zakljucajLabVezbu(int id) {
        LabVezba.updatelabVezba(2);
        Zaduzenja.azurirajZaduzenjaDem(id);
    }
    
    public static void zakljucajLabVezbuNastavnik(int id) {
        LabVezba.updatelabVezba(1);
        Zaduzenja.azurirajZaduzenjaNastDem(id);
    }
    
    public static void obracunajLabVezbu(int id) {
        LabVezba.updatelabVezbaAdmin(2, id);
        Zaduzenja.obracunajDemosa(id);
    }
    
    public static String citajSifruPredmeta(int idLabVez) {
        ResultSet rs;
        rs = DB.executeQuery("SELECT predmet FROM labvezba WHERE idLabVezbe = '" + idLabVez + "'");
        String sifra;
        try {
            while (rs.next()) {
                sifra = rs.getString("predmet");
                return sifra;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LabVezba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    
    
    public static LabVezba citajLabVezbuId(int id) {
        LabVezba lab = new LabVezba();
        ResultSet rs;
        rs = DB.executeQuery("select * from labvezba where idLabVezbe = '" + id + "'");
        try {
            while (rs.next()) {
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
            }
        } catch (SQLException ex) {
            Logger.getLogger(LabVezba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lab;
    }
    
    public static void azurirajTrenutnoPrijavljeno(int id, int tren) {
        DB.executeUpdate("update labvezba set trenutnoprijavljeno = '" + tren + "' where idLabVezbe = '" + id + "'");
    }
    
    public static void sacuvajIzmeneVezbe(LabVezba labV) {
        java.sql.Date sDate = new java.sql.Date(labV.getDatum().getTime());
        DB.executeUpdate("update labvezba set predmet = '" + labV.getPredmet() + "', naziv = '" + labV.getNaziv() + "', datum = '" 
                + sDate + "', vremeod = '" + labV.getVremeod() + "', vremedo = '" + labV.getVremedo() + "', laboratorija = '" 
                + labV.getLaboratorija() + "', tipaktivnosti = '" + labV.getTipaktivnosti() + "', maxbrojstudenata = '" 
                + labV.getMaxbrojstudenata() + "', trenutnoprijavljeno = '" + labV.getTrenutnoprijavljeno() + "', username = '" 
                + labV.getUsername() + "', kljuc = '" + labV.getKljuc() + "' where idLabVezbe = '" + labV.getIdLabVezbe() + "'");
    }
    
}

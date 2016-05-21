/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBeans;

import beans.LogInBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DB;

/**
 *
 * @author Nikola
 */
public class Zaduzenja {
    
    private int idLabVezbe;
    private String username;
    private String usernamedem;
    private String imedem;
    private String prezimedem;
    private int izjasnjenjedem;

    public Zaduzenja() {}

    public Zaduzenja(int idLabVezbe, String username, String usernamedem, String imedem, String prezimedem, int izjasnjenjedem) {
        this.idLabVezbe = idLabVezbe;
        this.username = username;
        this.usernamedem = usernamedem;
        this.imedem = imedem;
        this.prezimedem = prezimedem;
        this.izjasnjenjedem = izjasnjenjedem;
    }

    public int getIdLabVezbe() {
        return idLabVezbe;
    }

    public String getUsername() {
        return username;
    }

    public String getUsernamedem() {
        return usernamedem;
    }

    public String getImedem() {
        return imedem;
    }

    public String getPrezimedem() {
        return prezimedem;
    }

    public int getIzjasnjenjedem() {
        return izjasnjenjedem;
    }

    public void setIdLabVezbe(int idLabVezbe) {
        this.idLabVezbe = idLabVezbe;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernamedem(String usernamedem) {
        this.usernamedem = usernamedem;
    }

    public void setImedem(String imedem) {
        this.imedem = imedem;
    }

    public void setPrezimedem(String prezimedem) {
        this.prezimedem = prezimedem;
    }

    public void setIzjasnjenjedem(int izjasnjenjedem) {
        this.izjasnjenjedem = izjasnjenjedem;
    }
    
    public static ResultSet ucitajZaduzenjaNas() {
        ResultSet rs;
        rs = DB.executeQuery("select * from zaduzenja where username = '" + LogInBean.user.getUsername() + "'");
        return rs;
    }
    
    public static ResultSet ucitajZaduzenjaDem() {
        ResultSet rs;
        rs = DB.executeQuery("select * from zaduzenja where usernamedem = '" + LogInBean.user.getUsername() + "'");
        return rs;
    }
    
    public static ResultSet ucitajZaduzenjaDemNula() {
        ResultSet rs;
        rs = DB.executeQuery("select * from zaduzenja where usernamedem = '" + LogInBean.user.getUsername() + "' and izjasnjenjedem = '0'");
        return rs;
    }
    
    public static ResultSet ucitajZaduzenjaDemosa(int zaduzenje) {
        ResultSet rs;
        rs = DB.executeQuery("select * from zaduzenja where usernamedem = '" + LogInBean.user.getUsername() + "' and izjasnjenjedem = '"+ zaduzenje + "'");
        return rs;
    }
    
    public static void addZaduzenje(int idLabVezbe, String username, String usernamedem, String imedem, String prezimedem, int izjasnjenjedem) {
        DB.executeUpdate("insert into zaduzenja (idLabVezbe, username, usernamedem, imedem, prezimedem, izjasnjenjedem) values ('" + idLabVezbe + "', '" 
                + username + "', '" + usernamedem + "', '" + imedem  + "', '" + prezimedem  + "', '" + izjasnjenjedem + "')");
    }
    
    public static ResultSet ucitajZaduzenjaAdmin(int zad) {
        ResultSet rs;
        rs = DB.executeQuery("select * from zaduzenja where izjasnjenjedem = '"+ zad + "'");
        return rs;
    }
            
    public static boolean provera(String sifra) {
        ResultSet rs;
        rs = DB.executeQuery("selecr izjasnjenjedem from zaduzenja where usernamedem = '" + LogInBean.user.getUsername() + "' and sifraispita = '" + sifra + "'");
        try {
            if (rs.getInt("izjasnjenjedem") != 0) {
                return true;
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Zaduzenja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void obracunajDemosa(int id) {
        // 
        DB.executeUpdate("update zaduzenja set izjasnjenjedem = '3' where idLabVezbe = '" + id + "' and izjasnjenjedem = '2'");
    }
    
    public static void isplatiDemosa(int id, String uusername) {
        DB.executeUpdate("update zaduzenja set izjasnjenjedem = '4' where idLabVezbe = '" + id + "' and usernamedem = '" + uusername + "'");
    }
    
    
    public static void prihvatanjeVezbe(LabVezba lab) {
        DB.executeUpdate("update zaduzenja set izjasnjenjedem = '1' where idLabVezbe = '" + lab.getIdLabVezbe() + "' and usernamedem = '" + LogInBean.user.getUsername() + "'");
    }
    
    public static void odbijanjeVezbe(LabVezba lab) {
        DB.executeUpdate("update zaduzenja set izjasnjenjedem = '5' where idLabVezbe = '" + lab.getIdLabVezbe() + "' and usernamedem = '" + LogInBean.user.getUsername() + "'");
    }
    
    public static ResultSet ucitajZaduzenjeLabId(int id) {
        ResultSet rs;
        rs = DB.executeQuery("select * from zaduzenja where idLabVezbe = '" + id + "' and username = '" + LogInBean.user.getUsername()+ "'");
        return rs;
    }
    
    public static void azurirajZaduzenjaNastDem(int id) {
        DB.executeUpdate("update zaduzenja set izjasnjenjedem = '2' where idLabVezbe = '" + id + "' and izjasnjenjedem = '1'");
    }
    
    public static void azurirajZaduzenjaDem(int id) {
        DB.executeUpdate("update zaduzenja set izjasnjenjedem = '3' where idLabVezbe = '" + id + "' and izjasnjenjedem = '2'");
    }
    
    public static void izmeniZaduzenje(int idLabVezbe, String username, String usernamedem, String imedem, String prezimedem, int izjasnjenje) {
        DB.executeUpdate("update zaduzenja set izjasnjenjedem = '" + izjasnjenje + "' where idLabVezbe = '"  + idLabVezbe + "' and username = '" 
                + username + "' and usernamedem = '" + usernamedem + "' and imedem = '" + imedem  + "' and prezimedem = '" 
                + prezimedem + "' and izjasnjenjedem = '1'");
    }
    
}

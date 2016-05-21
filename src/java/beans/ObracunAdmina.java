/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.Demonstrator;
import dataBeans.Isplata;
import dataBeans.LabVezba;
import dataBeans.Obracun;
import dataBeans.Zaduzenja;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Nikola
 */
@ManagedBean (name="obracunAdm")
@RequestScoped
public class ObracunAdmina {
    
    private LinkedList<Zaduzenja> isplacenaZad;
    private LinkedList<Zaduzenja> neisplacenaZad;
    private LinkedList<Obracun> isplaceniObracuni;
    private LinkedList<Obracun> neisplaceniObracuni;
    
    public ObracunAdmina() {
        isplacenaZad = new LinkedList<Zaduzenja>();
        neisplacenaZad = new LinkedList<Zaduzenja>();
        isplacenaZad = ucitajZaduzenja(4);
        neisplacenaZad = ucitajZaduzenja(3);
        isplaceniObracuni = new LinkedList<Obracun>();
        neisplaceniObracuni = new LinkedList<Obracun>();
        isplaceniObracuni = popuniObracune(isplacenaZad);
        neisplaceniObracuni = popuniObracune(neisplacenaZad);
    }

    public void setIsplacenaZad(LinkedList<Zaduzenja> isplacenaZad) {
        this.isplacenaZad = isplacenaZad;
    }

    public void setNeisplacenaZad(LinkedList<Zaduzenja> neisplacenaZad) {
        this.neisplacenaZad = neisplacenaZad;
    }

    public void setIsplaceniObracuni(LinkedList<Obracun> isplaceniObracuni) {
        this.isplaceniObracuni = isplaceniObracuni;
    }

    public void setNeisplaceniObracuni(LinkedList<Obracun> neisplaceniObracuni) {
        this.neisplaceniObracuni = neisplaceniObracuni;
    }

    public LinkedList<Zaduzenja> getIsplacenaZad() {
        return isplacenaZad;
    }

    public LinkedList<Zaduzenja> getNeisplacenaZad() {
        return neisplacenaZad;
    }

    public LinkedList<Obracun> getIsplaceniObracuni() {
        return isplaceniObracuni;
    }

    public LinkedList<Obracun> getNeisplaceniObracuni() {
        return neisplaceniObracuni;
    }
    
    private LinkedList<Zaduzenja> ucitajZaduzenja(int zad) {
        LinkedList<Zaduzenja> zaduzenja = new LinkedList<Zaduzenja>();
        ResultSet rs = Zaduzenja.ucitajZaduzenjaAdmin(zad);
        try {
            while (rs.next()) {
                Zaduzenja z = new Zaduzenja();
                z.setIdLabVezbe(rs.getInt("idLabVezbe"));
                z.setUsername(rs.getString("username"));
                z.setUsernamedem(rs.getString("usernamedem"));
                z.setImedem(rs.getString("imedem"));
                z.setPrezimedem(rs.getString("prezimedem"));
                z.setIzjasnjenjedem(rs.getInt("izjasnjenjedem"));
                zaduzenja.add(z);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArhivaDemonstrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zaduzenja;
    }
    
    private LinkedList<Obracun> popuniObracune(LinkedList<Zaduzenja> zad) {
        LinkedList<Obracun> obracuni = new LinkedList<Obracun>();
        LabVezba lab = new LabVezba();
        Demonstrator dem = new Demonstrator();
        for (Zaduzenja z : zad) {
            Obracun obr = new Obracun();
            int id = z.getIdLabVezbe();
            lab = LabVezba.citajLabVezbuId(id);
            String us = z.getUsernamedem();
            dem = Demonstrator.citajDemosaUsername(us);
            obr.setUsername(dem.getUserName());
            obr.setIdLab(z.getIdLabVezbe());
            obr.setIme(dem.getName());
            obr.setPrezime(dem.getSurname());
            obr.setOdsek(dem.getOdsek());
            obr.setGodina(dem.getGodina());
            obr.setDatum(lab.getDatum());
            obr.setVremeOd(lab.getVremeod());
            obr.setVremeDo(lab.getVremedo());
            obr.setSuma(obr.izracunajSumu());
            obracuni.add(obr);
        }
        return obracuni;
    }
    
    public void isplatiDemosa(Obracun obr) {
        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Isplata.addIsplata(obr.getUsername(), "isplata", sqlDate, obr.getSuma());
        Zaduzenja.isplatiDemosa(obr.getIdLab(), obr.getUsername());
        this.neisplaceniObracuni.remove(obr);
        this.isplaceniObracuni.add(obr);
    }
    
}

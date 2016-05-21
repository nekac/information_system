/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.DemonstratorPredmet;
import dataBeans.LabVezba;
import dataBeans.Predmet;
import dataBeans.Zaduzenja;
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
@ManagedBean (name="predmetiDemos")
@RequestScoped
public class PredmetiDemos {
    
    private LinkedList<Predmet> mojiPredmeti;
    private LinkedList<DemonstratorPredmet> sifreMojihPredmeta;
    private LinkedList<Zaduzenja> mojaZaduzenja;
    
    public PredmetiDemos() {
        mojiPredmeti = ucitajPredmete();
        //mojiPredmeti = filtrirajPredmete(mojiPredmeti);
    }

    public LinkedList<Predmet> getMojiPredmeti() {
        return mojiPredmeti;
    }

    public LinkedList<DemonstratorPredmet> getSifreMojihPredmeta() {
        return sifreMojihPredmeta;
    }

    public void setMojiPredmeti(LinkedList<Predmet> mojiPredmeti) {
        this.mojiPredmeti = mojiPredmeti;
    }

    public void setSifreMojihPredmeta(LinkedList<DemonstratorPredmet> sifreMojihPredmeta) {
        this.sifreMojihPredmeta = sifreMojihPredmeta;
    }
    
    public LinkedList<DemonstratorPredmet> ucitajSifrePredmeta() {
        LinkedList<DemonstratorPredmet> mojeSifre = new LinkedList<DemonstratorPredmet>();
        ResultSet rs = DemonstratorPredmet.citajDemonstratorPredmet();
        try {
            while (rs.next()) {
                DemonstratorPredmet dp = new DemonstratorPredmet();
                dp.setUsername(rs.getString("username"));
                dp.setPredmet(rs.getString("predmet"));
                mojeSifre.add(dp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PredmetiDemos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mojeSifre;
    }
    
    public LinkedList<Zaduzenja> ucitajMojaZaduzenja() {
        LinkedList<Zaduzenja> zaduzenja = new LinkedList<Zaduzenja>();
        ResultSet rs = Zaduzenja.ucitajZaduzenjaDem();
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
            Logger.getLogger(PredmetiDemos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zaduzenja;
    }

    public LinkedList<Zaduzenja> getMojaZaduzenja() {
        return mojaZaduzenja;
    }

    public void setMojaZaduzenja(LinkedList<Zaduzenja> mojaZaduzenja) {
        this.mojaZaduzenja = mojaZaduzenja;
    }
    
    
    
    private LinkedList<Predmet> ucitajPredmete() {
        LinkedList<Predmet> predmeti = new LinkedList<Predmet>();
        sifreMojihPredmeta = ucitajSifrePredmeta();
        ResultSet rs;
        for (DemonstratorPredmet dp : sifreMojihPredmeta) {
            rs = Predmet.citajPredmeteSaSifrom(dp.getPredmet());
            try {
                while (rs.next()) {
                    Predmet pr = new Predmet();
                    pr.setSifra(rs.getString("sifra"));
                    pr.setNaziv(rs.getString("naziv"));
                    pr.setSemestar(rs.getString("semestar"));
                    pr.setSkolaskaGodina(rs.getString("skolska_godina"));
                    predmeti.add(pr);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PredmetiDemos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return predmeti;
    }
    
    private LinkedList<Predmet> filtrirajPredmete(LinkedList<Predmet> mojiPredmeti) {
        mojaZaduzenja = ucitajMojaZaduzenja();
        LinkedList<Predmet> pred = new LinkedList<Predmet>();
        for (Predmet p : mojiPredmeti) {
            for (Zaduzenja z : mojaZaduzenja) {
                if (p.getSifra().equals(LabVezba.citajSifruPredmeta(z.getIdLabVezbe()))) {
                    if (z.getIzjasnjenjedem() == 1)
                        pred.add(p);
                }
            }
        }
        return pred;
    }
    
}

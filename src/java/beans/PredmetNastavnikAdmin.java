/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.Nastavnik;
import dataBeans.NastavnikPredmet;
import dataBeans.Predmet;
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
@ManagedBean (name="dodajNastPred")
@RequestScoped
public class PredmetNastavnikAdmin {
    
    private LinkedList<Nastavnik> nastavnici;
    private LinkedList<Predmet> predmeti;
    
    private LinkedList<String> nastavniciString;
    private LinkedList<String> predmetiString;
    
    private Nastavnik nast;
    private Predmet pred;
    
    private String nastav;
    private String predm;
    
    public PredmetNastavnikAdmin() {
        nastavnici = ucitajNastavnike();
        predmeti = ucitajPredmete();
        nastavniciString = nastavnikToString(nastavnici);
        predmetiString = predmetToString(predmeti);
    }

    public void setNastavniciString(LinkedList<String> nastavniciString) {
        this.nastavniciString = nastavniciString;
    }

    public void setPredmetiString(LinkedList<String> predmetiString) {
        this.predmetiString = predmetiString;
    }

    public void setNastav(String nastav) {
        this.nastav = nastav;
    }

    public void setPredm(String predm) {
        this.predm = predm;
    }

    public LinkedList<String> getNastavniciString() {
        return nastavniciString;
    }

    public LinkedList<String> getPredmetiString() {
        return predmetiString;
    }

    public String getNastav() {
        return nastav;
    }

    public String getPredm() {
        return predm;
    }

    public void setNastavnici(LinkedList<Nastavnik> nastavnici) {
        this.nastavnici = nastavnici;
    }

    public void setPredmeti(LinkedList<Predmet> predmeti) {
        this.predmeti = predmeti;
    }

    public void setNast(Nastavnik nast) {
        this.nast = nast;
    }

    public void setPred(Predmet pred) {
        this.pred = pred;
    }

    public LinkedList<Nastavnik> getNastavnici() {
        return nastavnici;
    }

    public LinkedList<Predmet> getPredmeti() {
        return predmeti;
    }

    public Nastavnik getNast() {
        return nast;
    }

    public Predmet getPred() {
        return pred;
    }
    
    private LinkedList<Predmet> ucitajPredmete() {
        ResultSet rs;
        LinkedList<Predmet> pred = new LinkedList<Predmet>();
        rs = Predmet.citajPredmete();
        try {
            while (rs.next()) {
                Predmet pr = new Predmet();
                pr.setSifra(rs.getString("sifra"));
                pr.setNaziv(rs.getString("naziv"));
                pr.setSemestar(rs.getString("semestar"));
                pr.setSkolaskaGodina(rs.getString("skolska_godina"));
                pred.add(pr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PredmetNastavnikAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pred;
    }
    
    private LinkedList<Nastavnik> ucitajNastavnike() {
        ResultSet rs;
        LinkedList<Nastavnik> nast = new LinkedList<Nastavnik>();
        rs = Nastavnik.ucitajSveNastavnike();
        try {
            while (rs.next()) {
                Nastavnik n = new Nastavnik();
                n.setUserName(rs.getString("username"));
                n.setPassword(rs.getString("password"));
                n.setName(rs.getString("name"));
                n.setSurname(rs.getString("surname"));
                n.setTelNumber(rs.getString("phone_number"));
                n.setEmail(rs.getString("email"));
                n.setImage(rs.getString("picture"));
                nast.add(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Nastavnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nast;
    }
    
    private LinkedList<String> nastavnikToString(LinkedList<Nastavnik> nast) {
        LinkedList<String> nastString = new LinkedList<String>();
        for (Nastavnik n : nast) {
            String str = "";
            str += n.getName() + "-" + n.getSurname() + "-" + n.getUserName() + "-" + n.getTelNumber();
            nastString.add(str);
        }
        return nastString;
    }
    
    private LinkedList<String> predmetToString(LinkedList<Predmet> pred) {
        LinkedList<String> predString = new LinkedList<String>();
        for (Predmet p : pred) {
            String str = "";
            str += p.getSifra() + "-" + p.getNaziv() + "-" + p.getSemestar() + "-" + p.getSkolaskaGodina();
            predString.add(str);
        }
        return predString;
    }
    
    public Nastavnik stringToNastavnik(String str) {
        Nastavnik nas;
        nas = this.nastavnici.get(this.nastavniciString.indexOf(str));
        return nas;
    }
    
    public Predmet stringToPredmet(String str) {
        Predmet pr;
        pr = this.predmeti.get(this.predmetiString.indexOf(str));
        return pr;
    }
    
    public void dodaj() {
        if (this.nastav.equals("") || this.predm.equals("")) return;
        this.nast = stringToNastavnik(this.nastav);
        this.pred = stringToPredmet(this.predm);
        NastavnikPredmet.addNastavnikPredmet(nast.getUserName(), pred.getSifra());
    }
    
}

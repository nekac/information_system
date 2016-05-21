/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
@ManagedBean (name="ucitajPredmete")
@RequestScoped
public class UcitajPredmete {
    
    private LinkedList<Predmet> sviPredmeti;
    private LinkedList<Predmet> mojiPredmeti;
    private LinkedList<Predmet> prikaziPredmeti;
    
    private String flag;
    
    private String filterPredmet;
    private String filterSifra;
    
    public UcitajPredmete() {
        sviPredmeti = ucitajSvePredmete();
        mojiPredmeti = ucitajMojePredmete(sviPredmeti);
        prikaziPredmeti = sviPredmeti;
        flag = "svi";
    }

    public void setMojiPredmeti(LinkedList<Predmet> mojiPredmeti) {
        this.mojiPredmeti = mojiPredmeti;
    }

    public void setPrikaziPredmeti(LinkedList<Predmet> prikaziPredmeti) {
        this.prikaziPredmeti = prikaziPredmeti;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public LinkedList<Predmet> getMojiPredmeti() {
        return mojiPredmeti;
    }

    public LinkedList<Predmet> getPrikaziPredmeti() {
        return prikaziPredmeti;
    }

    public String getFlag() {
        return flag;
    }

    public LinkedList<Predmet> getSviPredmeti() {
        return sviPredmeti;
    }

    public void setSviPredmeti(LinkedList<Predmet> sviPredmeti) {
        this.sviPredmeti = sviPredmeti;
    }

    public String getFilterPredmet() {
        return filterPredmet;
    }

    public String getFilterSifra() {
        return filterSifra;
    }

    public void setFilterPredmet(String filterPredmet) {
        this.filterPredmet = filterPredmet;
    }

    public void setFilterSifra(String filterSifra) {
        this.filterSifra = filterSifra;
    }
    
    public String ucitajFlagMojiPr() {
        flag = "moji";
        return flag;
    }
    
    public String ucitajFlagSviPr() {
        flag = "svi";
        return flag;
    }
    
    public String ucitajFlagOdabraniPr() {
        flag = "odabrani";
        return flag;
    }
    
    public static LinkedList<Predmet> ucitajSvePredmete() {
        ResultSet rs = Predmet.citajPredmete();
        LinkedList<Predmet> pred = new LinkedList<Predmet>();
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
            Logger.getLogger(PretragaNastavnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pred;
    }
    
    public static LinkedList<NastavnikPredmet> ucitajVezuNastavnikPredmet() {
        ResultSet rs = NastavnikPredmet.ucitajNastavnikPredmet();
        LinkedList<NastavnikPredmet> nastPr = new LinkedList<NastavnikPredmet>();
        try {
            while (rs.next()) {
                NastavnikPredmet np = new NastavnikPredmet();
                np.setUsername(rs.getString("username"));
                np.setPredmet(rs.getString("predmet"));
                nastPr.add(np);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UcitajPredmete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nastPr;
    }
    
    public static LinkedList<Predmet> ucitajMojePredmete(LinkedList<Predmet> predmeti) {
        LinkedList<Predmet> pred = new LinkedList<Predmet>();
        LinkedList<NastavnikPredmet> nastPred = ucitajVezuNastavnikPredmet();
        for (Predmet p : predmeti) {
            for (NastavnikPredmet np : nastPred) {
                if (p.getSifra().equals(np.getPredmet())) {
                    pred.add(p);
                    break;
                }
            }
        }
        return pred;
    }
    
    public void handleActionMethodPredmet() {
        if (flag.equals("svi")) {
            prikaziPredmeti = sviPredmeti;
        }
        if (flag.equals("moji")) {
            prikaziPredmeti = mojiPredmeti;
        }
        if (flag.equals("odabrani")) {
            prikaziPredmeti = mojiPredmeti;
        }
    }
    
}

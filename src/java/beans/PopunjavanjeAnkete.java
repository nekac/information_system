/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.Anketa;
import dataBeans.AnketiraniDemonstratori;
import dataBeans.DemonstratorPredmet;
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
@ManagedBean (name="popunjavanjeAnkete")
@RequestScoped
public class PopunjavanjeAnkete {
    
    private LinkedList<Predmet> izabraniPredmeti;
    private LinkedList<Predmet> sviPredmeti;
    private boolean anketiran;
    private static LinkedList<Predmet> cuvanjeIzabranih;
    
    public PopunjavanjeAnkete() {
        anketiran = proveriAnketiran();
        ucitajPredmete();
    }

    public void setCuvanjeIzabranih(LinkedList<Predmet> cuvanjeIzabranih) {
        PopunjavanjeAnkete.cuvanjeIzabranih = cuvanjeIzabranih;
    }

    public LinkedList<Predmet> getCuvanjeIzabranih() {
        return cuvanjeIzabranih;
    }

    public boolean isAnketiran() {
        return anketiran;
    }

    public void setAnketiran(boolean anketiran) {
        this.anketiran = anketiran;
    }
    
    public LinkedList<Predmet> getIzabraniPredmeti() {
        return izabraniPredmeti;
    }

    public LinkedList<Predmet> getSviPredmeti() {
        return sviPredmeti;
    }

    public void setIzabraniPredmeti(LinkedList<Predmet> izabraniPredmeti) {
        this.izabraniPredmeti = izabraniPredmeti;
    }

    public void setSviPredmeti(LinkedList<Predmet> sviPredmeti) {
        this.sviPredmeti = sviPredmeti;
    }
    
    private void ucitajPredmete() {
        sviPredmeti = new LinkedList<Predmet>();
        ResultSet rs;
        rs = Anketa.citajAnketu();
        try {
            while (rs.next()) {
                Predmet p = new Predmet();
                p.setSifra(rs.getString("sifra"));
                p.setNaziv(rs.getString("naziv"));
                p.setSemestar(rs.getString("semestar"));
                p.setSkolaskaGodina(rs.getString("skolska_godina"));
                sviPredmeti.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PopunjavanjeAnkete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void izaberiPredmet(Predmet p) {
        if (cuvanjeIzabranih == null) {
            cuvanjeIzabranih = new LinkedList<Predmet>();
        }
        if (cuvanjeIzabranih.contains(p)) {
            cuvanjeIzabranih.remove(p);
        } else {
            cuvanjeIzabranih.add(p);
        }
        //cuvanjeIzabranih = izabraniPredmeti;
    }
    
    public void sacuvajIzbor() {
        //this.izabraniPredmeti = cuvanjeIzabranih;
        for (Predmet p : cuvanjeIzabranih) {
            DemonstratorPredmet.addDemonstratorPredmet(LogInBean.user.getUsername(), p.getSifra());
        }
        AnketiraniDemonstratori.addAnketiraniDemos(1);
        cuvanjeIzabranih = null;
    }
    
    private boolean proveriAnketiran() {
        int broj = AnketiraniDemonstratori.ucitajAnketiraniDemos();
        if (broj == 0) return false;
        return true;
    }
    
    public String postaviCheck(Predmet p) {
        p.setCheck(true);
        return null;
    }
    
    public String sacuvaj() {
        izabraniPredmeti = new LinkedList<Predmet>();
        for (Predmet pr : this.sviPredmeti) {
            if (pr.isCheck()) {
                izabraniPredmeti.add(pr);
            }
        }
        for (Predmet pred : izabraniPredmeti) {
            DemonstratorPredmet.addDemonstratorPredmet(LogInBean.user.getUsername(), pred.getSifra());
        }
        AnketiraniDemonstratori.addAnketiraniDemos(1);
        sviPredmeti = null;
        return null;
    }
    
}

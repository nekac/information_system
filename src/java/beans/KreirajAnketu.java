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
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Nikola
 */
@ManagedBean (name="kreirajAnk")
@SessionScoped
public class KreirajAnketu {
    
    private String skolskaGodina;
    private LinkedList<Predmet> predmeti = new LinkedList<Predmet>();
    private boolean visible = false;
    
    public void KreirajAnketu() {

    }
    
    public void brisiAnketu() {
        Anketa.isprazniAnketu();
    }
    
    public void uzmiList() {
        setVisible(true);
    }
    
    public void kreirajAnketu() {
        this.ucitajPredmete();
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public String getSkolskaGodina() {
        return skolskaGodina;
    }

    public LinkedList<Predmet> getPredmeti() {
        return predmeti;
    }

    public void setSkolskaGodina(String skolskaGodina) {
        this.skolskaGodina = skolskaGodina;
    }

    public void setPredmeti(LinkedList<Predmet> predmeti) {
        this.predmeti = predmeti;
    }
    
    public void ucitajPredmete() {
        ResultSet rs = Predmet.citajTekucePredmete(this.skolskaGodina);
        try {
            while (rs.next()) {
                Predmet pr = new Predmet();
                pr.setSifra(rs.getString("sifra"));
                pr.setNaziv(rs.getString("naziv"));
                pr.setSemestar(rs.getString("semestar"));
                pr.setSkolaskaGodina(rs.getString("skolska_godina"));
                this.predmeti.add(pr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KreirajAnketu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void posaljiAnketu() {
        if (!this.predmeti.isEmpty()) {
            Anketa.isprazniAnketu();
            for (Predmet p : this.predmeti) {
                Anketa.addPredmet(p.getSifra(), p.getNaziv(), p.getSemestar(), p.getSkolaskaGodina());
            }
            AnketiraniDemonstratori.isprazniAnketirane();
            DemonstratorPredmet.isprazniDemPred();
        } else {
            
        }
    }
    
    public void ucitajAnketu() {
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
        
    }
        
    
}

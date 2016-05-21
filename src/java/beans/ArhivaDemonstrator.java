/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.LabVezba;
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
@ManagedBean (name="arhivaDemonstrator")
@RequestScoped
public class ArhivaDemonstrator {
    
    LinkedList<Zaduzenja> mojaIsplacenaZaduzenja;
    LinkedList<Zaduzenja> mojaNeisplacenaZaduzenja;
    LinkedList<LabVezba> mojeIsplaceneLabVezbe;
    LinkedList<LabVezba> mojeNeisplaceneLabVezbe;
    
    public ArhivaDemonstrator() {
        mojaIsplacenaZaduzenja = ucitajMojaArhiviranaZaduzenja(4);
        mojaNeisplacenaZaduzenja = ucitajMojaArhiviranaZaduzenja(2);
        mojaNeisplacenaZaduzenja.addAll(ucitajMojaArhiviranaZaduzenja(3));
        mojeIsplaceneLabVezbe = ucitajMojeArhiviraneLabVezbe(mojaIsplacenaZaduzenja);
        mojeNeisplaceneLabVezbe = ucitajMojeArhiviraneLabVezbe(mojaNeisplacenaZaduzenja);
    }

    public void setMojeIsplaceneLabVezbe(LinkedList<LabVezba> mojeIsplaceneLabVezbe) {
        this.mojeIsplaceneLabVezbe = mojeIsplaceneLabVezbe;
    }

    public void setMojeNeisplaceneLabVezbe(LinkedList<LabVezba> mojeNeisplaceneLabVezbe) {
        this.mojeNeisplaceneLabVezbe = mojeNeisplaceneLabVezbe;
    }

    public LinkedList<LabVezba> getMojeIsplaceneLabVezbe() {
        return mojeIsplaceneLabVezbe;
    }

    public LinkedList<LabVezba> getMojeNeisplaceneLabVezbe() {
        return mojeNeisplaceneLabVezbe;
    }

    public LinkedList<Zaduzenja> getMojaIsplacenaZaduzenja() {
        return mojaIsplacenaZaduzenja;
    }

    public LinkedList<Zaduzenja> getMojaNeisplacenaZaduzenja() {
        return mojaNeisplacenaZaduzenja;
    }

    public void setMojaIsplacenaZaduzenja(LinkedList<Zaduzenja> mojaIsplacenaZaduzenja) {
        this.mojaIsplacenaZaduzenja = mojaIsplacenaZaduzenja;
    }

    public void setMojaNeisplacenaZaduzenja(LinkedList<Zaduzenja> mojaNeisplacenaZaduzenja) {
        this.mojaNeisplacenaZaduzenja = mojaNeisplacenaZaduzenja;
    }
    
    private LinkedList<Zaduzenja> ucitajMojaArhiviranaZaduzenja(int zad) {
        LinkedList<Zaduzenja> zaduzenja = new LinkedList<Zaduzenja>();
        ResultSet rs = Zaduzenja.ucitajZaduzenjaDemosa(zad);
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
    
    private LinkedList<LabVezba> ucitajMojeArhiviraneLabVezbe(LinkedList<Zaduzenja> listaZaduzenja) {
        LinkedList<LabVezba> vezbe = new LinkedList<LabVezba>();
        for (Zaduzenja z : listaZaduzenja) {
            LabVezba lab = new LabVezba();
            lab = LabVezba.citajLabVezbuId(z.getIdLabVezbe());
            vezbe.add(lab);
        }
        return vezbe;
    }
    
    
}

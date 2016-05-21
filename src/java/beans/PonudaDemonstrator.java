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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Nikola
 */
@ManagedBean (name="ponudeDemos")
@RequestScoped
public class PonudaDemonstrator {
    
    private LinkedList<Zaduzenja> mojaZaduzenja = ucitajMojaNepotvrdjenaZaduzenja();
    private LinkedList<LabVezba> labVezbe = ucitajPonuduLabVezbi();
    
    private String komentar;
    

    public PonudaDemonstrator() {
        //mojaZaduzenja = ucitajMojaNepotvrdjenaZaduzenja();
        //labVezbe = ucitajPonuduLabVezbi();
        if (komentar == null) komentar = "";
    }

    public LinkedList<Zaduzenja> getMojaZaduzenja() {
        return mojaZaduzenja;
    }

    public LinkedList<LabVezba> getLabVezbe() {
        return labVezbe;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setMojaZaduzenja(LinkedList<Zaduzenja> mojaZaduzenja) {
        this.mojaZaduzenja = mojaZaduzenja;
    }

    public void setLabVezbe(LinkedList<LabVezba> labVezbe) {
        this.labVezbe = labVezbe;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
    
    private LinkedList<Zaduzenja> ucitajMojaNepotvrdjenaZaduzenja() {
        ResultSet rs = Zaduzenja.ucitajZaduzenjaDemNula();
        LinkedList<Zaduzenja> mojaZad = new LinkedList<Zaduzenja>();
        try {
            while (rs.next()) {
                Zaduzenja z = new Zaduzenja();
                z.setIdLabVezbe(rs.getInt("idLabVezbe"));
                z.setUsername(rs.getString("username"));
                z.setUsernamedem(rs.getString("usernamedem"));
                z.setImedem(rs.getString("imedem"));
                z.setPrezimedem(rs.getString("prezimedem"));
                z.setIzjasnjenjedem(rs.getInt("izjasnjenjedem"));
                mojaZad.add(z);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PretragaDemonstrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mojaZad;
    }
    
    private LinkedList<LabVezba> ucitajPonuduLabVezbi() {
        LinkedList<LabVezba> labVez = new LinkedList<LabVezba>();
        for (Zaduzenja z : mojaZaduzenja) {
            ResultSet rs = LabVezba.ucitajSveMojeLabVezbeDemos(LabVezba.citajSifruPredmeta(z.getIdLabVezbe()), z.getUsername());
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
                    labVez.add(lab);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UcitajLabVezbu.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        return labVez;
    }
    
    public void prihvatiVezbu(LabVezba lab) {
        if (lab.getMaxbrojstudenata() == lab.getTrenutnoprijavljeno()) {
            Zaduzenja.odbijanjeVezbe(lab);
            //popunjeno, izbaci alert
            this.labVezbe.remove(lab);
        } else {
            Zaduzenja.prihvatanjeVezbe(lab);
            LabVezba.azurirajTrenutnoPrijavljeno(lab.getIdLabVezbe(), lab.getTrenutnoprijavljeno()+1);
            this.labVezbe.remove(lab);
        }
    }
    
    public void odbijVezbu(LabVezba lab) {
        Zaduzenja.odbijanjeVezbe(lab);
        this.labVezbe.remove(lab);
    }
    
}

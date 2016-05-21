/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.Demonstrator;
import dataBeans.LabVezba;
import dataBeans.NastavnikPredmet;
import dataBeans.Predmet;
import dataBeans.Zaduzenja;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Nikola
 */
@ManagedBean (name="pretragaNastavnik")
@ViewScoped
@SessionScoped
public class PretragaNastavnik {

    private String ime;
    private String prezime;
    
    private String filterIme;
    private String filterPrezime;
    private String filterPredmet;
    private String filterSifra;
    
    private static LinkedList<Demonstrator> sviDemonstratori;
    
    private LinkedList<Demonstrator> sviMojiDemonstratori = new LinkedList<Demonstrator>();
    
    private static LinkedList<Predmet> sviPredmeti;
    
    private LinkedList<Predmet> sviMojiPredmeti = new LinkedList<Predmet>();
    
    private LinkedList<Predmet> odabraniPredmeti = new LinkedList<Predmet>();
    
    private LinkedList<Zaduzenja> svaMojaZaduzenja = new LinkedList<Zaduzenja>();
    
    private LinkedList<NastavnikPredmet> vezaNastavnikPredmet = new LinkedList<NastavnikPredmet>(); 
    
    private LinkedList<Demonstrator> pozvaniDemonstratori = new LinkedList<Demonstrator>();
    
    static {
        //sviDemonstratori = new LinkedList<Demonstrator>();
        //sveSifre = new LinkedList<Sifarnik>();
        //sviPredmeti = new LinkedList<Predmet>();
        //ucitajDemonstratore();
        //ucitajPredmete();
        //ucitajSifarnik();
    }
    
    public PretragaNastavnik() {}

    public PretragaNastavnik(String ime, String prezime, String filterIme, String filterPrezime, String filterPredmet, String filterSifra) {
        this.ime = ime;
        this.prezime = prezime;
        this.filterIme = filterIme;
        this.filterPrezime = filterPrezime;
        this.filterPredmet = filterPredmet;
        this.filterSifra = filterSifra;
    }

    

    public LinkedList<Demonstrator> getPozvaniDemonstratori() {
        return pozvaniDemonstratori;
    }

    public void setPozvaniDemonstratori(LinkedList<Demonstrator> pozvaniDemonstratori) {
        this.pozvaniDemonstratori = pozvaniDemonstratori;
    }

    public String getFilterPredmet() {
        return filterPredmet;
    }

    public void setFilterPredmet(String filterPredmet) {
        this.filterPredmet = filterPredmet;
    }

    public String getFilterSifra() {
        return filterSifra;
    }

    public void setFilterSifra(String filterSifra) {
        this.filterSifra = filterSifra;
    }

    public String getFilterIme() {
        return filterIme;
    }

    public String getFilterPrezime() {
        return filterPrezime;
    }

    public void setFilterIme(String filterIme) {
        this.filterIme = filterIme;
    }

    public void setFilterPrezime(String filterPrezime) {
        this.filterPrezime = filterPrezime;
    }

    public LinkedList<Demonstrator> getSviMojiDemonstratori() {
        return sviMojiDemonstratori;
    }

    public LinkedList<Predmet> getOdabraniPredmeti() {
        return odabraniPredmeti;
    }

    public LinkedList<Zaduzenja> getSvaMojaZaduzenja() {
        return svaMojaZaduzenja;
    }

    public LinkedList<NastavnikPredmet> getVezaNastavnikPredmet() {
        return vezaNastavnikPredmet;
    }

    public void setSviMojiDemonstratori(LinkedList<Demonstrator> sviMojiDemonstratori) {
        this.sviMojiDemonstratori = sviMojiDemonstratori;
    }

    public void setOdabraniPredmeti(LinkedList<Predmet> odabraniPredmeti) {
        this.odabraniPredmeti = odabraniPredmeti;
    }

    public void setSvaMojaZaduzenja(LinkedList<Zaduzenja> svaMojaZaduzenja) {
        this.svaMojaZaduzenja = svaMojaZaduzenja;
    }

    public void setVezaNastavnikPredmet(LinkedList<NastavnikPredmet> vezaNastavnikPredmet) {
        this.vezaNastavnikPredmet = vezaNastavnikPredmet;
    }

    public LinkedList<Demonstrator> getSviDemonstratori() {
        return sviDemonstratori;
    }

    public LinkedList<Predmet> getSviPredmeti() {
        return sviPredmeti;
    }

    public LinkedList<Predmet> getSviMojiPredmeti() {
        return sviMojiPredmeti;
    }

    public void setSviDemonstratori(LinkedList<Demonstrator> sviDemonstratori) {
        this.sviDemonstratori = sviDemonstratori;
    }

    public void setSviPredmeti(LinkedList<Predmet> sviPredmeti) {
        this.sviPredmeti = sviPredmeti;
    }

    public void setSviMojiPredmeti(LinkedList<Predmet> sviMojiPredmeti) {
        this.sviMojiPredmeti = sviMojiPredmeti;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    
    
    
    public static void ucitajDemonstratore() {
        ResultSet rs = Demonstrator.citajDemonstratore();
        sviDemonstratori.clear();
        try {
            while (rs.next()) {
                Demonstrator dem = new Demonstrator();
                dem.setUserName(rs.getString("username"));
                dem.setPassword(rs.getString("password"));
                dem.setName(rs.getString("name"));
                dem.setSurname(rs.getString("surname"));
                dem.setTelNumber(rs.getString("phone_number"));
                dem.setEmail(rs.getString("email"));
                dem.setImage(rs.getString("picture"));
                dem.setOdsek(rs.getString("odsek"));
                dem.setGodina(rs.getInt("godina"));
                dem.setProsek(rs.getDouble("prosek"));
                sviDemonstratori.add(dem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PretragaNastavnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String ucitajPredmete() {
        ResultSet rs = Predmet.citajPredmete();
        sviPredmeti.clear();
        try {
            while (rs.next()) {
                Predmet pr = new Predmet();
                pr.setSifra(rs.getString("sifra"));
                pr.setNaziv(rs.getString("naziv"));
                pr.setSemestar(rs.getString("semestar"));
                pr.setSkolaskaGodina(rs.getString("skolska_godina"));
                sviPredmeti.add(pr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PretragaNastavnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "nastavnikPredmet";
    }
    
    public void ucitajVezaNastavnikPredmet() {
        ResultSet rs = NastavnikPredmet.ucitajNastavnikPredmet();
        vezaNastavnikPredmet.clear();
        try {
            while (rs.next()) {
                NastavnikPredmet np = new NastavnikPredmet();
                np.setUsername(rs.getString("username"));
                np.setPredmet(rs.getString("predmet"));
                vezaNastavnikPredmet.add(np);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PretragaNastavnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ucitajSviMojiPredmeti() {
        sviMojiPredmeti.clear();
        ucitajVezaNastavnikPredmet();
        ucitajPredmete();
        for (NastavnikPredmet np : vezaNastavnikPredmet) {
            Predmet pr = null;
            for (Predmet p : sviPredmeti) {
                if (np.getPredmet().equals(p.getSifra())) {
                    pr = p;
                    break;
                }
            }
            if (pr != null) {
                sviMojiPredmeti.add(pr);
            }
        }
    }
    
    public void ucitajSvaMojaZaduzenja() {
        ResultSet rs = Zaduzenja.ucitajZaduzenjaNas();
        svaMojaZaduzenja.clear();
        try {
            while (rs.next()) {
                Zaduzenja z = new Zaduzenja();
                z.setIdLabVezbe(rs.getInt("idLabVezbe"));
                z.setUsername(rs.getString("username"));
                z.setUsernamedem(rs.getString("usernamedem"));
                z.setImedem(rs.getString("imedem"));
                z.setPrezimedem(rs.getString("prezimedem"));
                z.setIzjasnjenjedem(rs.getInt("izjasnjenjedem"));
                svaMojaZaduzenja.add(z);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PretragaNastavnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ucitajNaSvojimPredmetima() {
        sviMojiDemonstratori.clear();
        ucitajDemonstratore();
        ucitajSvaMojaZaduzenja();
        for (Demonstrator d : sviDemonstratori) {
            Demonstrator dem = null;
            for (Zaduzenja z : svaMojaZaduzenja) {
                if (d.getName().equals(z.getImedem()) && d.getSurname().equals(z.getPrezimedem())) {
                    dem = d;
                    sviMojiDemonstratori.add(dem);
                    break;
                }
            }
        }
    }
    
    public void ucitajNaOdabranimPredmetima() {
        sviMojiDemonstratori.clear();
        ucitajDemonstratore();
        ucitajSvaMojaZaduzenja();
        for (Demonstrator d : sviDemonstratori) {
            Demonstrator dem = null;
            for (Predmet p : odabraniPredmeti) { //pazi na clear!!!
                for (Zaduzenja z : svaMojaZaduzenja) {
                    if (p.getSifra().equals(LabVezba.citajSifruPredmeta(z.getIdLabVezbe())) && d.getName().equals(z.getImedem()) && d.getSurname().equals(z.getPrezimedem()) && z.getIzjasnjenjedem() == 1) {
                    dem = d;
                    sviMojiDemonstratori.add(dem);
                    break;
                    }
                }
            }
        }
    }
    
    
}

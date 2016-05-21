/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.*;
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
@ManagedBean (name="ucitajDemonstratore")
@RequestScoped
@SessionScoped
public class UcitajDemonstratore {
    
    private LinkedList<Demonstrator> sviDemonstratori;
    private LinkedList<Demonstrator> mojiDemonstratori;
    private LinkedList<Demonstrator> predmetDemonstratori;

    private LinkedList<Demonstrator> imeDemonstratori;
    private LinkedList<Demonstrator> prezimeDemonstratori;
    private LinkedList<Demonstrator> prikazDemonstratori;
    
    private String flag;
    
    private String filterIme;
    private String filterPrezime;
    
    private static Nastavnik nast = new Nastavnik();
    private static Demonstrator demos = new Demonstrator();
    private static boolean potvrdjivanje = false;
    
    private Nastavnik nastavnik = nast;
    
    public UcitajDemonstratore() {
        sviDemonstratori = ucitajSveDemonstratore();
        mojiDemonstratori = ucitajMojeDemonstratore(sviDemonstratori);
        predmetDemonstratori = ucitajPredmetDemonstratore(sviDemonstratori);
        imeDemonstratori = ucitajMojeDemonstratore(sviDemonstratori);
        prezimeDemonstratori = ucitajMojeDemonstratore(sviDemonstratori);
        prikazDemonstratori = sviDemonstratori;
        flag = "svi";
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }
    
    public void setNast(Nastavnik nast) {
        UcitajDemonstratore.nast = nast;
    }

    public Nastavnik getNast() {
        return nast;
    }

        public LinkedList<Demonstrator> getPredmetDemonstratori() {
        return predmetDemonstratori;
    }

    public void setPredmetDemonstratori(LinkedList<Demonstrator> predmetDemonstratori) {
        this.predmetDemonstratori = predmetDemonstratori;
    }
    
    public void setPotvrdjivanje(boolean potvrdjivanje) {
        UcitajDemonstratore.potvrdjivanje = potvrdjivanje;
    }

    public boolean isPotvrdjivanje() {
        return potvrdjivanje;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public void setMojiDemonstratori(LinkedList<Demonstrator> mojiDemonstratori) {
        this.mojiDemonstratori = mojiDemonstratori;
    }

    public void setPrikazDemonstratori(LinkedList<Demonstrator> prikazDemonstratori) {
        this.prikazDemonstratori = prikazDemonstratori;
    }

    public LinkedList<Demonstrator> getMojiDemonstratori() {
        return mojiDemonstratori;
    }

    public LinkedList<Demonstrator> getPrikazDemonstratori() {
        return prikazDemonstratori;
    }

    public Demonstrator getDemos() {
        return demos;
    }

    public void setDemos(Demonstrator demos) {
        UcitajDemonstratore.demos = demos;
    }

    public LinkedList<Demonstrator> getSviDemonstratori() {
        return sviDemonstratori;
    }

    public String getFilterIme() {
        return filterIme;
    }

    public String getFilterPrezime() {
        return filterPrezime;
    }

    public void setSviDemonstratori(LinkedList<Demonstrator> sviDemonstratori) {
        this.sviDemonstratori = sviDemonstratori;
    }

    public void setFilterIme(String filterIme) {
        this.filterIme = filterIme;
    }

    public void setFilterPrezime(String filterPrezime) {
        this.filterPrezime = filterPrezime;
    }
    
    
    
    public static LinkedList<Demonstrator> ucitajSveDemonstratore() {
        ResultSet rs = Demonstrator.citajDemonstratore();
        LinkedList<Demonstrator> demosi = new LinkedList<Demonstrator>();
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
                demosi.add(dem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PretragaNastavnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return demosi;
    }
    
    public static LinkedList<Zaduzenja> ucitajMojaZaduzenja() {
        ResultSet rs = Zaduzenja.ucitajZaduzenjaNas();
        LinkedList<Zaduzenja> zad = new LinkedList<Zaduzenja>();
        try {
            while (rs.next()) {
                Zaduzenja z = new Zaduzenja();
                z.setIdLabVezbe(rs.getInt("idLabVezbe"));
                z.setUsername(rs.getString("username"));
                z.setUsernamedem(rs.getString("usernamedem"));
                z.setImedem(rs.getString("imedem"));
                z.setPrezimedem(rs.getString("prezimedem"));
                z.setIzjasnjenjedem(rs.getInt("izjasnjenjedem"));
                zad.add(z);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PretragaDemonstrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zad;
    }
    
    public static LinkedList<Demonstrator> ucitajMojeDemonstratore(LinkedList<Demonstrator> sviDem) {
        LinkedList<Demonstrator> mojiDem = new LinkedList<Demonstrator>();
        LinkedList<Zaduzenja> zad = ucitajMojaZaduzenja();
        for (Demonstrator d : sviDem) {
            for (Zaduzenja z : zad) {
                if (d.getUserName().equals(z.getUsernamedem())) {
                    mojiDem.add(d);
                    break;
                }
            }
        }
        return mojiDem;
    }
    public static LinkedList<Predmet> ucitajMojePredmete() {
        ResultSet rs = Predmet.citajPredmete();
        LinkedList<Predmet> pre = new LinkedList<Predmet>();
        try {
            while (rs.next()) {
                Predmet p = new Predmet();
                p.setNaziv(rs.getString("naziv"));
                p.setSemestar(rs.getString("semestar"));
                p.setSifra(rs.getString("sifra"));
                p.setSkolaskaGodina(rs.getString("skolskaGodina"));
                pre.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PretragaDemonstrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pre;
    }
    public static LinkedList<DemonstratorPredmet> ucitajPredmeteDemonstratore() {
        ResultSet rs = NastavnikPredmet.ucitajNastavnikPredmet();
        LinkedList<DemonstratorPredmet> dempre = new LinkedList<DemonstratorPredmet>();
        try {
            while (rs.next()) {
                DemonstratorPredmet dp = new DemonstratorPredmet();
                dp.setUsername(rs.getString("username"));
                dp.setPredmet(rs.getString("predmet"));
                dempre.add(dp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PretragaDemonstrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dempre;
    }
    
    
    
    public static LinkedList<Demonstrator> ucitajPredmetDemonstratore(LinkedList<Demonstrator> sviDem) {
        LinkedList<Demonstrator> predmetDem = new LinkedList<Demonstrator>();
        LinkedList<DemonstratorPredmet> poklapajuciPredmeti = new LinkedList<DemonstratorPredmet>();
        LinkedList<DemonstratorPredmet> predDem = ucitajPredmeteDemonstratore();
        LinkedList<Predmet> pred = ucitajMojePredmete();
        for (Predmet p : pred) {
                for (DemonstratorPredmet dp : predDem){
                    if (dp.getPredmet().equals(p.getSifra())){
                        poklapajuciPredmeti.add(dp);
                        break;
                    }                           
                }
            }
        
        for (Demonstrator d : sviDem) {
            for (DemonstratorPredmet pp : poklapajuciPredmeti){
                if (pp.getUsername().equals(d.getUserName())){
                     predmetDem.add(d);
                     break;
                }
            }
        }
        return predmetDem;
    }
    
    public String sacuvajDemosa(Demonstrator d) {
        demos = d;
        return "pogledajDemonstratora";
    }
    
    public String sacuvajNast(Nastavnik na) {
        nast = na;
        return "pogledajNastavnika";
    }
    
    public String ucitajFlagMoji() {
        flag = "moji";
        return flag;
    }
    
    public String ucitajFlagSvi() {
        flag = "svi";
        return flag;
    }
    
    public String ucitajFlagOdabraniPr() {
        flag = "odabrani";
        return flag;
    }
    
    public String ucitajFlagIme() {
        flag = "ime";
        return flag;
    }
    
    public String ucitajFlagPrezime() {
        flag = "prezime";
        return flag;
    }
    
    public void handleActionMethod() {
        if (flag.equals("svi")) {
            prikazDemonstratori = sviDemonstratori;
        }
        if (flag.equals("moji")) {
            prikazDemonstratori = mojiDemonstratori;
        }        
        if (flag.equals("odabrani")) {
            prikazDemonstratori = predmetDemonstratori;
        }
        
    }
    
    public String nazad() {
        if (LogInBean.user.getType() == 0) {
            potvrdjivanje = true;
        }
        if (potvrdjivanje) {
            if (LogInBean.user.getType() == 0) {
            potvrdjivanje = false;
            }
            return "potvrdjivanjeRegistracija";
        } else {
            return "nastavnikDemonstrator";
        }
    }
    
    public String ucitajSliku(Nastavnik na) {
        return na.getImage();
    }
    
}

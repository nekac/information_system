/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.Demonstrator;
import dataBeans.DemonstratorString;
import dataBeans.LabVezba;
import dataBeans.Nastavnik;
import dataBeans.NastavnikPredmet;
import dataBeans.Predmet;
import dataBeans.Sifarnik;
import dataBeans.Zaduzenja;
import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Nikola
 */
@ManagedBean (name="kreiranjeLabVezbe")
@SessionScoped
public class KreiranjeLabVezbe {
    
    private String nazivVezbe;
    private java.util.Date datum;
    private Time vremeOd;
    private Time vremeDo;
    private String laboratorija;
    private int tipAktivnosti;
    private int maxDemosa;
    private LinkedList<Demonstrator> sviDemosi;
    private String aktivnost;
    private LinkedList<String> aktivnosti;
    private LinkedList<Sifarnik> sifre;
    private Predmet mojPredmet;
    private LinkedList<Predmet> mojiPredmeti;
    private LinkedList<String> predmeti;
    private String predmet;
    private int vremeSatiOd;
    private int vremeSatiDo;
    private int vremeMinutiOd;
    private int vremeMinutiDo;
    private LinkedList<Demonstrator> izabraniDemosi;
    private LinkedList<DemonstratorString> sviDemosiString;
    private LinkedList<DemonstratorString> izabraniDemosiString;
    private LinkedList<String> stringDemSvi;
    private LinkedList<String> stringDemIzabrani;
            
    public KreiranjeLabVezbe() {
        sviDemosi = UcitajDemonstratore.ucitajSveDemonstratore();
        aktivnosti = new LinkedList<String>();
        mojiPredmeti = new LinkedList<Predmet>();
        predmeti = new LinkedList<String>();
        izabraniDemosi = new LinkedList<Demonstrator>();
        sifre = UcitajSifarnik.ucitajSifarnik();
        for (Sifarnik s : sifre) {
            String str = "";
            str += s.getSifra() + "-" + s.getNazivAktivnosti() + "-" + s.getKoeficijent();
            aktivnosti.add(str);
        }
        mojiPredmeti = ucitajMojePredmete();
        for (Predmet p : mojiPredmeti) {
            String str = "";
            str += p.getSifra() + "-" + p.getNaziv() + "-" + p.getSemestar() + "-" + p.getSkolaskaGodina();
            predmeti.add(str);
        }
        sviDemosiString = this.demosToString(sviDemosi);
        izabraniDemosiString = new LinkedList<DemonstratorString>();
        stringDemSvi = new LinkedList<String>();
        stringDemIzabrani = new LinkedList<String>();
        for (Demonstrator d : sviDemosi) {
            String str = "";
            str += d.getName() + "-" + d.getSurname() + "-" + d.getOdsek() + "-" + d.getGodina() + "-" + d.getProsek();
            stringDemSvi.add(str);
        }
    }

    public KreiranjeLabVezbe(String nazivVezbe, Date datum, Time vremeOd, Time vremeDo, String laboratorija, int tipAktivnosti, int maxDemosa) {
        this.nazivVezbe = nazivVezbe;
        this.datum = datum;
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
        this.laboratorija = laboratorija;
        this.tipAktivnosti = tipAktivnosti;
        this.maxDemosa = maxDemosa;
    }

    public void setStringDemSvi(LinkedList<String> stringDemSvi) {
        this.stringDemSvi = stringDemSvi;
    }

    public void setStringDemIzabrani(LinkedList<String> stringDemIzabrani) {
        this.stringDemIzabrani = stringDemIzabrani;
    }

    public LinkedList<String> getStringDemSvi() {
        return stringDemSvi;
    }

    public LinkedList<String> getStringDemIzabrani() {
        return stringDemIzabrani;
    }

    public LinkedList<DemonstratorString> getSviDemosiString() {
        return sviDemosiString;
    }

    public LinkedList<DemonstratorString> getIzabraniDemosiString() {
        return izabraniDemosiString;
    }

    public void setSviDemosiString(LinkedList<DemonstratorString> sviDemosiString) {
        this.sviDemosiString = sviDemosiString;
    }

    public void setIzabraniDemosiString(LinkedList<DemonstratorString> izabraniDemosiString) {
        this.izabraniDemosiString = izabraniDemosiString;
    }
    
    public LinkedList<Demonstrator> getIzabraniDemosi() {
        return izabraniDemosi;
    }

    public void setIzabraniDemosi(LinkedList<Demonstrator> izabraniDemosi) {
        this.izabraniDemosi = izabraniDemosi;
    }

    public int getVremeSatiOd() {
        return vremeSatiOd;
    }

    public int getVremeSatiDo() {
        return vremeSatiDo;
    }

    public int getVremeMinutiOd() {
        return vremeMinutiOd;
    }

    public int getVremeMinutiDo() {
        return vremeMinutiDo;
    }

    public void setVremeSatiOd(int vremeSatiOd) {
        this.vremeSatiOd = vremeSatiOd;
    }

    public void setVremeSatiDo(int vremeSatiDo) {
        this.vremeSatiDo = vremeSatiDo;
    }

    public void setVremeMinutiOd(int vremeMinutiOd) {
        this.vremeMinutiOd = vremeMinutiOd;
    }

    public void setVremeMinutiDo(int vremeMinutiDo) {
        this.vremeMinutiDo = vremeMinutiDo;
    }

    public Predmet getMojPredmet() {
        return mojPredmet;
    }

    public LinkedList<Predmet> getMojiPredmeti() {
        return mojiPredmeti;
    }

    public LinkedList<String> getPredmeti() {
        return predmeti;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setMojPredmet(Predmet mojPredmet) {
        this.mojPredmet = mojPredmet;
    }

    public void setMojiPredmeti(LinkedList<Predmet> mojiPredmeti) {
        this.mojiPredmeti = mojiPredmeti;
    }

    public void setPredmeti(LinkedList<String> predmeti) {
        this.predmeti = predmeti;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public LinkedList<String> getAktivnosti() {
        return aktivnosti;
    }

    public LinkedList<Sifarnik> getSifre() {
        return sifre;
    }

    public void setAktivnosti(LinkedList<String> aktivnosti) {
        this.aktivnosti = aktivnosti;
    }

    public void setSifre(LinkedList<Sifarnik> sifre) {
        this.sifre = sifre;
    }

    public String getAktivnost() {
        return aktivnost;
    }

    public void setAktivnost(String aktivnost) {
        this.aktivnost = aktivnost;
    }

    public String getNazivVezbe() {
        return nazivVezbe;
    }

    public java.util.Date getDatum() {
        return datum;
    }

    public Time getVremeOd() {
        return vremeOd;
    }

    public Time getVremeDo() {
        return vremeDo;
    }

    public String getLaboratorija() {
        return laboratorija;
    }

    public int getTipAktivnosti() {
        return tipAktivnosti;
    }

    public int getMaxDemosa() {
        return maxDemosa;
    }

    public LinkedList<Demonstrator> getSviDemosi() {
        return sviDemosi;
    }

    public void setNazivVezbe(String nazivVezbe) {
        this.nazivVezbe = nazivVezbe;
    }

    public void setDatum(java.util.Date datum) {
        this.datum = datum;
    }

    public void setVremeOd(Time vremeOd) {
        this.vremeOd = vremeOd;
    }

    public void setVremeDo(Time vremeDo) {
        this.vremeDo = vremeDo;
    }

    public void setLaboratorija(String laboratorija) {
        this.laboratorija = laboratorija;
    }

    public void setTipAktivnosti(int tipAktivnosti) {
        this.tipAktivnosti = tipAktivnosti;
    }

    public void setMaxDemosa(int maxDemosa) {
        this.maxDemosa = maxDemosa;
    }

    public void setSviDemosi(LinkedList<Demonstrator> sviDemosi) {
        this.sviDemosi = sviDemosi;
    }
    
    public void ubaciTipAktivnosti() {
        int redBr = this.aktivnosti.indexOf(aktivnost);
        this.tipAktivnosti = redBr;
    } 
    
    public void ubaciPredmet() {
        int redBr = this.predmeti.indexOf(predmet);
        this.mojPredmet = this.mojiPredmeti.get(redBr);
    }
    
    public LinkedList<Predmet> ucitajMojePredmete() {
        LinkedList<Predmet> mojiPred = new LinkedList<Predmet>();
        LinkedList<Predmet> sviPredmeti = UcitajPredmete.ucitajSvePredmete();
        LinkedList<NastavnikPredmet> vezaNastavnikPredmet = UcitajNastavnikPredmet.ucitajVezaNastavnikPredmet();
        for (NastavnikPredmet np : vezaNastavnikPredmet) {
            Predmet pr = null;
            for (Predmet p : sviPredmeti) {
                if (np.getPredmet().equals(p.getSifra())) {
                    pr = p;
                    break;
                }
            }
            if (pr != null) {
                mojiPred.add(pr);
            }
        }
        return mojiPred;
    }
    
    public void kreirajVezbu() {
        this.vremeOd = new Time(this.vremeSatiOd, this.vremeMinutiOd, 0);
        this.vremeDo = new Time(this.vremeSatiDo, this.vremeMinutiDo, 0);
        this.ubaciTipAktivnosti();
        this.ubaciPredmet();
        this.izabraniDemosiString = this.ekstrakcija(this.stringDemIzabrani);
        this.izabraniDemosi = this.stringToDemos(this.izabraniDemosiString);
        int id = LabVezba.addLabVezba(mojPredmet.getSifra(), nazivVezbe, this.convertJavaDateToSqlDate(datum), vremeOd, vremeDo, laboratorija, tipAktivnosti, maxDemosa, 0, LogInBean.user.getUsername(), 0);
        //int id = LabVezba.citajIdLabVezbe(mojPredmet.getSifra(), nazivVezbe, this.convertJavaDateToSqlDate(datum), vremeOd, vremeDo, laboratorija, tipAktivnosti, maxDemosa, 0, LogInBean.user.getUsername(), 0);
        Nastavnik nas = LogInBean.nastavnik;
        for (Demonstrator d : izabraniDemosi) {
            Zaduzenja.addZaduzenje(id, nas.getUserName(), d.getUserName(), d.getName(), d.getSurname(), 0);
        }
    }
    
    public LinkedList<DemonstratorString> ekstrakcija(LinkedList<String> list) {
        LinkedList<DemonstratorString> demosString = new LinkedList<DemonstratorString>();
        for (String str : this.stringDemSvi) {
            if (list.contains(str)) {
                demosString.add(this.sviDemosiString.get(this.stringDemSvi.indexOf(str)));
            }
        }
        return demosString;
    }
    
    public java.sql.Date convertJavaDateToSqlDate(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
    
    public LinkedList<Demonstrator> stringToDemos(LinkedList<DemonstratorString> stringList) {
        LinkedList<Demonstrator> demosList = new LinkedList<Demonstrator>();
        for (DemonstratorString ds : stringList) {
            Demonstrator demos = new Demonstrator();
            demos.setUserName(ds.getUserName());
            demos.setPassword(ds.getPassword());
            demos.setName(ds.getName());
            demos.setSurname(ds.getSurname());
            demos.setTelNumber(ds.getTelNumber());
            demos.setEmail(ds.getEmail());
            demos.setImage(ds.getImage());
            demos.setOdsek(ds.getOdsek());
            demos.setGodina(Integer.parseInt(ds.getGodina()));
            demos.setProsek(Double.parseDouble(ds.getProsek()));
            demosList.add(demos);
        }
        return demosList;
    }
    
    public LinkedList<DemonstratorString> demosToString(LinkedList<Demonstrator> demList) {
        LinkedList<DemonstratorString> stringList = new LinkedList<DemonstratorString>();
        for (Demonstrator d : demList) {
            DemonstratorString ds = new DemonstratorString();
            ds.setUserName(d.getUserName());
            ds.setPassword(d.getPassword());
            ds.setName(d.getName());
            ds.setSurname(d.getSurname());
            ds.setTelNumber(d.getTelNumber());
            ds.setEmail(d.getEmail());
            ds.setImage(d.getImage());
            ds.setOdsek(d.getOdsek());
            ds.setGodina(Integer.toString(d.getGodina()));
            ds.setProsek(Double.toString(d.getProsek()));
            stringList.add(ds);
        }
        return stringList;
    }
    
}

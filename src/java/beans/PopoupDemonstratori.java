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
@ManagedBean (name="popupDem")
@RequestScoped
public class PopoupDemonstratori {
    
    private LinkedList<Demonstrator> sviDemosi;
    private LinkedList<Demonstrator> izabraniDemosi;
    private LinkedList<DemonstratorString> sviDemosiString;
    private LinkedList<DemonstratorString> izabraniDemosiString;
    private LinkedList<String> stringDemSvi;
    private LinkedList<String> stringDemIzabrani = new LinkedList<String>();
    private LinkedList<Demonstrator> postojeciDemosi;
    private LinkedList<String> stringPostojeciDemosi;
    private static int labId = 0;
            
    public PopoupDemonstratori() {
        sviDemosi = UcitajDemonstratore.ucitajSveDemonstratore();
        izabraniDemosi = new LinkedList<Demonstrator>();
        sviDemosiString = this.demosToString(sviDemosi);
        izabraniDemosiString = new LinkedList<DemonstratorString>();
        stringDemSvi = new LinkedList<String>();
        //stringDemIzabrani = new LinkedList<String>();
        for (Demonstrator d : sviDemosi) {
            String str = "";
            str += d.getName() + "-" + d.getSurname() + "-" + d.getOdsek() + "-" + d.getGodina() + "-" + d.getProsek();
            stringDemSvi.add(str);
        }
        
    }

    public void setLabId(int labId) {
        PopoupDemonstratori.labId = labId;
    }

    public int getLabId() {
        return labId;
    }

    public void setStringPostojeciDemosi(LinkedList<String> stringPostojeciDemosi) {
        this.stringPostojeciDemosi = stringPostojeciDemosi;
    }

    public LinkedList<String> getStringPostojeciDemosi() {
        return stringPostojeciDemosi;
    }

    public void setSviDemosi(LinkedList<Demonstrator> sviDemosi) {
        this.sviDemosi = sviDemosi;
    }

    public void setIzabraniDemosi(LinkedList<Demonstrator> izabraniDemosi) {
        this.izabraniDemosi = izabraniDemosi;
    }

    public void setSviDemosiString(LinkedList<DemonstratorString> sviDemosiString) {
        this.sviDemosiString = sviDemosiString;
    }

    public void setIzabraniDemosiString(LinkedList<DemonstratorString> izabraniDemosiString) {
        this.izabraniDemosiString = izabraniDemosiString;
    }

    public void setStringDemSvi(LinkedList<String> stringDemSvi) {
        this.stringDemSvi = stringDemSvi;
    }

    public void setStringDemIzabrani(LinkedList<String> stringDemIzabrani) {
        this.stringDemIzabrani = stringDemIzabrani;
    }

    public void setPostojeciDemosi(LinkedList<Demonstrator> postojeciDemosi) {
        this.postojeciDemosi = postojeciDemosi;
    }

    public LinkedList<Demonstrator> getSviDemosi() {
        return sviDemosi;
    }

    public LinkedList<Demonstrator> getIzabraniDemosi() {
        return izabraniDemosi;
    }

    public LinkedList<DemonstratorString> getSviDemosiString() {
        return sviDemosiString;
    }

    public LinkedList<DemonstratorString> getIzabraniDemosiString() {
        return izabraniDemosiString;
    }

    public LinkedList<String> getStringDemSvi() {
        return stringDemSvi;
    }

    public LinkedList<String> getStringDemIzabrani() {
        return stringDemIzabrani;
    }

    public LinkedList<Demonstrator> getPostojeciDemosi() {
        return postojeciDemosi;
    }
    
    public void ucitaj(LabVezba labb) {
        labId = labb.getIdLabVezbe();
        
        postojeciDemosi = ucitajPostojeceDemose();
        stringPostojeciDemosi = new LinkedList<String>();
        for (Demonstrator d : postojeciDemosi) {
            String str = "";
            str += d.getName() + "-" + d.getSurname() + "-" + d.getOdsek() + "-" + d.getGodina() + "-" + d.getProsek();
            stringPostojeciDemosi.add(str);
        }
    }
    
    public LinkedList<Demonstrator> ucitajPostojeceDemose() { 
        LinkedList<Demonstrator> postojeci = new LinkedList<Demonstrator>();
        LinkedList<Zaduzenja> zad = new LinkedList<Zaduzenja>();
        ResultSet rs = Zaduzenja.ucitajZaduzenjeLabId(labId);
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
            Logger.getLogger(PopoupDemonstratori.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Zaduzenja z : zad) {
            if (z.getIzjasnjenjedem() == 1) {
                Demonstrator dem = Demonstrator.citajDemosaUsername(z.getUsernamedem());
                postojeci.add(dem);
            }
        }
        return postojeci;
    }
    
    public String dodajDemonstratore() {
        this.izabraniDemosiString = ekstrakcija(stringDemIzabrani);
        this.izabraniDemosi = this.stringToDemos(this.izabraniDemosiString);
        Nastavnik nas = LogInBean.nastavnik;
        for (Demonstrator d : izabraniDemosi) {
            Zaduzenja.addZaduzenje(labId, nas.getUserName(), d.getUserName(), d.getName(), d.getSurname(), 1);
        }
        return null;
    }
    
    public String izbaciDemonstratora(String str) {
        LinkedList<String> list = new LinkedList<String>();
        list.add(str);
        LinkedList<DemonstratorString> demosStr = ekstrakcija(list);
        LinkedList<Demonstrator> demosi = stringToDemos(demosStr);
        Demonstrator d = demosi.getFirst();
        Nastavnik nas = LogInBean.nastavnik;
        Zaduzenja.addZaduzenje(labId, nas.getUserName(), d.getUserName(), d.getName(), d.getSurname(), 5);
        this.postojeciDemosi.remove(d);
        this.stringPostojeciDemosi.remove(str);
        return null;
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
    
    private LinkedList<DemonstratorString> demosToString(LinkedList<Demonstrator> demList) {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.Predmet;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author Nikola
 */
@ManagedBean (name="dodajPredmet")
@RequestScoped
public class DodajPredmet {
    
    private String sifra;
    private String naziv;
    private String semestar;
    private String skolaskaGodina;
    
    private UIComponent component;
    
    public UIComponent getComponent() {
        return component;
    }
    public void setComponent(UIComponent component) {
        this.component = component;
    }
    
    public DodajPredmet() {}

    public String getSifra() {
        return sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getSemestar() {
        return semestar;
    }

    public String getSkolaskaGodina() {
        return skolaskaGodina;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setSemestar(String semestar) {
        this.semestar = semestar;
    }

    public void setSkolaskaGodina(String skolaskaGodina) {
        this.skolaskaGodina = skolaskaGodina;
    }
    
    public String dodajPredmet() {
        FacesContext context = FacesContext.getCurrentInstance();
        Predmet.addPredmet(sifra, naziv, semestar, skolaskaGodina);
        context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Predmet uspešno registrovan."));
        return null;
    }
    
}

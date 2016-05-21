/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.Nastavnik;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Nikola
 */
@ManagedBean (name="infoNastavnik")
@SessionScoped
public class InfoNastavnik {

    public Nastavnik nastavnik;
    
    static {
        //nastavnik = Nastavnik.citajNastavnika(LogInBean.user.getUsername());
    }

    public InfoNastavnik() {}

    public InfoNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }
    
}

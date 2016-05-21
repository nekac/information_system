/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.Sifarnik;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Nikola
 */
@ManagedBean (name="ucitajSifarnik")
public class UcitajSifarnik {
    
    private LinkedList<Sifarnik> ceoSifarnik;
    
    public UcitajSifarnik() {
        ceoSifarnik = ucitajSifarnik();
    }

    public LinkedList<Sifarnik> getCeoSifarnik() {
        return ceoSifarnik;
    }

    public void setCeoSifarnik(LinkedList<Sifarnik> ceoSifarnik) {
        this.ceoSifarnik = ceoSifarnik;
    }
    
    
    
    public static LinkedList<Sifarnik> ucitajSifarnik() {
        ResultSet rs = Sifarnik.ucitajSifre();
        LinkedList<Sifarnik> sveSifre = new LinkedList<Sifarnik>();
        try {
            while (rs.next()) {
                Sifarnik s = new Sifarnik();
                s.setSifra(rs.getInt("sifra"));
                s.setNazivAktivnosti(rs.getString("naziv_aktivnosti"));
                s.setKoeficijent(rs.getDouble("koeficijent"));
                sveSifre.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PretragaNastavnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sveSifre;
    }
    
    
}

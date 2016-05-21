/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.Demonstrator;
import dataBeans.Zaduzenja;
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
@ManagedBean (name = "pretragaDemonstrator")
public class PretragaDemonstrator {

    private Demonstrator demos = new Demonstrator();
    
    private LinkedList<Zaduzenja> ponude = new LinkedList<Zaduzenja>();
    
    private LinkedList<Zaduzenja> angazovanja = new LinkedList<Zaduzenja>();
    
    
    
    public void ucitajPonude() {
        ResultSet rs = Zaduzenja.ucitajZaduzenjaDem();
        ponude.clear();
        try {
            while (rs.next()) {
                Zaduzenja z = new Zaduzenja();
                z.setIdLabVezbe(rs.getInt("idLabVezbe"));
                z.setUsername(rs.getString("username"));
                z.setUsernamedem(rs.getString("usernamedem"));
                z.setImedem(rs.getString("imedem"));
                z.setPrezimedem(rs.getString("prezimedem"));
                z.setIzjasnjenjedem(rs.getInt("izjasnjenjedem"));
                ponude.add(z);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PretragaDemonstrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

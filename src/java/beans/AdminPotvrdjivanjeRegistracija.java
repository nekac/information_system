/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.Demonstrator;
import dataBeans.Nastavnik;
import dataBeans.User;
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
@ManagedBean (name="potvrdjivanjeReg")
@RequestScoped
@SessionScoped
public class AdminPotvrdjivanjeRegistracija {

    private LinkedList<User> users = new LinkedList<User>();
    private LinkedList<Nastavnik> nastavnici = new LinkedList<Nastavnik>();
    private LinkedList<Demonstrator> demonstratori = new LinkedList<Demonstrator>();
    
    
    
    public AdminPotvrdjivanjeRegistracija() {
        users = ucitajUsers();
        nastavnici = ucitajNastavnike(users);
        demonstratori = ucitajDemonstratore(users);
    }

    public void setUsers(LinkedList<User> users) {
        this.users = users;
    }

    public void setNastavnici(LinkedList<Nastavnik> nastavnici) {
        this.nastavnici = nastavnici;
    }

    public void setDemonstratori(LinkedList<Demonstrator> demonstratori) {
        this.demonstratori = demonstratori;
    }

    public LinkedList<User> getUsers() {
        return users;
    }

    public LinkedList<Nastavnik> getNastavnici() {
        return nastavnici;
    }

    public LinkedList<Demonstrator> getDemonstratori() {
        return demonstratori;
    }
    
    private LinkedList<User> ucitajUsers() {
        ResultSet rs;
        LinkedList<User> us = new LinkedList<User>();
        rs = User.getUserValid();
        try {
            while (rs.next()) {
                User u = new User();
                u.setUsername(rs.getString("Username"));
                u.setPassword(rs.getString("Password"));
                u.setType(rs.getInt("type"));
                u.setValid(rs.getInt("valid"));
                us.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminPotvrdjivanjeRegistracija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }
     
    private LinkedList<Nastavnik> ucitajNastavnike(LinkedList<User> us) {
        LinkedList<Nastavnik> nas = new LinkedList<Nastavnik>();
        for (User u : us) {
            if (u.getType() == 1) {
                Nastavnik n;
                n = Nastavnik.ucitajNastavnika(u.getUsername());
                nas.add(n);
            }
        }
        return nas;
    }
    
    private LinkedList<Demonstrator> ucitajDemonstratore(LinkedList<User> us) {
        LinkedList<Demonstrator> dem = new LinkedList<Demonstrator>();
        for (User u : us) {
            if (u.getType() == 2) {
                Demonstrator d;
                d = Demonstrator.citajDemosaUsername(u.getUsername());
                dem.add(d);
            }
        }
        return dem;
    }
    
    public void registrujNastavnika(Nastavnik n) {
        User.setUserValid(n.getUserName());
        this.nastavnici.remove(n);
    }
    
    public void odbaciNastavnika(Nastavnik n) {
        User.odbaciUsera(n.getUserName());
        this.nastavnici.remove(n);
    }
    
    public void registrujDemonstratora(Demonstrator d) {
        User.setUserValid(d.getUserName());
        this.demonstratori.remove(d);
    }
    
    public void odbaciDemonstratora(Demonstrator d) {
        User.odbaciUsera(d.getUserName());
        this.demonstratori.remove(d);
    }
    
    
}

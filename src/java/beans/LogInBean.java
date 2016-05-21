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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import utilities.DB;

/**
 *
 * @author Nikola
 */
@ManagedBean(name="logIn")
@SessionScoped
public class LogInBean {
    
    private String username;
    private String password;
    
    public static Nastavnik nastavnik;
    public static Demonstrator demonstrator;
    
    public static User user;
    
    private UIComponent component;
    public UIComponent getComponent() {
        return component;
    }
    public void setComponent(UIComponent component) {
        this.component = component;
    }
    
    public LogInBean() {}

    public LogInBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public Demonstrator getDemonstrator() {
        return demonstrator;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public void setDemonstrator(Demonstrator demonstrator) {
        this.demonstrator = demonstrator;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        LogInBean.user = user;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
   
    public String logInUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        String ret = "";
        LogInBean.user = User.getUser(username);
        if (!user.getUsername().equals(" ")) {
            if (password.equals(user.getPassword())) {
                switch (user.getType()) {
                case 0: {
                    ret = "admin";
                    return ret;
                }
                case 1: {
                    if (user.getValid() == 1) {
                        ret = "nastavnik";
                        citajNastavnika(username);
                        return ret;
                    } else {
                        context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Vaš nalog nije aktivan."));
                        return ret;
                    }
                }
                case 2: {
                    if (user.getValid() == 1) {
                        ret = "demonstrator";
                        ucitajDemosa(username);
                        return ret;
                    } else {
                        context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Vaš nalog nije aktivan."));
                        return ret;
                    }
                } 
                }
            } else {
                context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Pogrešna šifra."));
                return ret;
            }
        } else {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Korisnik sa datim korisničkim imenom ne postoji."));
            return ret;
        }
        return ret;
    }
    
    public String logOutUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        return "index";
    }
    
    public static void citajNastavnika(String username) {
        ResultSet rs;
        rs = DB.executeQuery("select * from nastavnik where username = '" + username + "'");
        nastavnik = new Nastavnik();
        try {
            while (rs.next()) {
                nastavnik.setUserName(rs.getString("username"));
                nastavnik.setPassword(rs.getString("password"));
                nastavnik.setName(rs.getString("name"));
                nastavnik.setSurname(rs.getString("surname"));
                nastavnik.setTelNumber(rs.getString("phone_number"));
                nastavnik.setEmail(rs.getString("email"));
                nastavnik.setImage(rs.getString("picture"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UcitajLabVezbu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ucitajDemosa(String username) {
        ResultSet rs;
        rs = DB.executeQuery("select * from demonstrator where username = '" + username + "'");
        demonstrator = new Demonstrator();
        try {
            while (rs.next()) {
                demonstrator.setUserName(rs.getString("username"));
                demonstrator.setPassword(rs.getString("password"));
                demonstrator.setName(rs.getString("name"));
                demonstrator.setSurname(rs.getString("surname"));
                demonstrator.setTelNumber(rs.getString("phone_number"));
                demonstrator.setEmail(rs.getString("email"));
                demonstrator.setImage(rs.getString("picture"));
                demonstrator.setOdsek(rs.getString("odsek"));
                demonstrator.setGodina(rs.getInt("godina"));
                demonstrator.setProsek(rs.getDouble("prosek"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Demonstrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.Demonstrator;
import dataBeans.Nastavnik;
import dataBeans.User;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author Nikola
 */
@ManagedBean (name="promenaLozinke")
@RequestScoped
public class PromenaLozinke {
    
    private String username;
    private String password;
    private String newPassword;
    
    private UIComponent component;
    public UIComponent getComponent() {
        return component;
    }
    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public PromenaLozinke() {}
    
    public PromenaLozinke(String username, String password, String newPassword) {
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    public String updatePassword() {
        FacesContext context = FacesContext.getCurrentInstance();
        User currUser = User.getUser(username);
            if ((currUser.getValid() == 1) && password.equals(currUser.getPassword())) {
                switch (currUser.getType()) {
                case 0: {
                    break;
                }
                case 1: {
                    User.updateUser(username, newPassword);
                    Nastavnik.updateNastavnik(username, newPassword);
                    break;
                }
                case 2: {
                    User.updateUser(username, newPassword);
                    Demonstrator.updateDemonstrator(username, newPassword);
                    break;
                }
                }
            } else {
                context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pogresna lozinka!", "Pokusajte ponovo!"));
                return null;
            }
        return "index";
    }
    
}

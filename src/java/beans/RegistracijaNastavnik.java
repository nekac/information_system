
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
@ManagedBean (name="registracijaNastavnik")
@RequestScoped
public class RegistracijaNastavnik {
    
    private String userName;
    private String password;
    private String passwordConfirm;
    private String name;
    private String surname;
    private String telNumber;
    private String email;
    private String image;

    public RegistracijaNastavnik() {}
    
    private UIComponent component;
    
    public UIComponent getComponent() {
        return component;
    }
    public void setComponent(UIComponent component) {
        this.component = component;
    }
    
    public RegistracijaNastavnik(String userName, String password, String passwordConfirm, String name, String surname, String telNumber, String email, String image) {
        this.userName = userName;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.name = name;
        this.surname = surname;
        this.telNumber = telNumber;
        this.email = email;
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String registracijaNastavnika() {
        FacesContext context = FacesContext.getCurrentInstance();
        User user = User.getUser(userName);
        if (!user.getUsername().equals(" ")) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Korisnik sa datim korisničkim imenom već postoji u sistemu."));
            return null;
        }
        String upload;
        upload = UploadController.upload(userName);
        if (!upload.equals("Uspesno!")) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Korisnik nije uneo sliku."));
            return null;
        }
        image = userName + ".jpg";
        if(password.equals(passwordConfirm)){
            Nastavnik.addNastavnik(userName, password, name, surname, telNumber, email, image, 0);
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Korisnik uspešno registrovan."));
            return null;
        } else {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Šifre se ne poklapaju."));
            return null;
        }
    }
    
}

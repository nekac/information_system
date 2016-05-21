/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dataBeans.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nikola
 */
@ManagedBean (name="navigation")
@RequestScoped
public class NavigationBean {
    
    public String getHomepage(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        User currUser = LogInBean.user;
        if(currUser.getType() == 0)
            return "admin";
        else if(currUser.getType() == 1)
            return "nastavnik";
        else if(currUser.getType() == 2)
            return "demonstrator";
        return "";
    }
    
    public static String redirect(int type){
        if(type == 0)
            return "admin";
        else if(type == 1)
            return "nastavnik";
        else if(type == 2)
            return "demonstrator";
        return "";
    }
    
}

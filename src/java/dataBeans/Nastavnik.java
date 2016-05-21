/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBeans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import utilities.DB;

/**
 *
 * @author Nikola
 */
@ManagedBean
public class Nastavnik {
    
    private String userName;
    private String password;
    private String name;
    private String surname;
    private String telNumber;
    private String email;
    private String image;

    public Nastavnik() {}
    
    public Nastavnik(String userName, String password, String name, String surname, String telNumber, String email, String image) {
        this.userName = userName;
        this.password = password;
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
    
    public static void addNastavnik(String userName, String password, String name, String surname, String telNumber, String email, String image, int valid) {
        DB.executeUpdate("insert into nastavnik (username, password, name, surname, phone_number, email, picture)"
                + "values('" + userName + "', '" + password + "', '" + name + "', '" + surname + "', '"
                + telNumber + "', '" + email + "', '" + image + "')");
        DB.executeUpdate("insert into user (username, password, type, valid) values ('" + userName + "', '" + password + "', '1', '" + valid + "')");
    }
    
    public static void updateNastavnik(String uusername, String ppassword) {
        DB.executeUpdate("update nastavnik set password = '" + ppassword + "' where username = '" + uusername + "'");
    }
    
    public static Nastavnik ucitajNastavnika(String uusername) {
        ResultSet rs;
        Nastavnik n = new Nastavnik();
        rs = DB.executeQuery("select * from nastavnik  where username = '" + uusername + "'");
        try {
            while (rs.next()) {
                n.setUserName(rs.getString("username"));
                n.setPassword(rs.getString("password"));
                n.setName(rs.getString("name"));
                n.setSurname(rs.getString("surname"));
                n.setTelNumber(rs.getString("phone_number"));
                n.setEmail(rs.getString("email"));
                n.setImage(rs.getString("picture"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Nastavnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public static ResultSet ucitajSveNastavnike() {
        ResultSet rs;
        rs = DB.executeQuery("select * from nastavnik");
        return rs;
    }
    
}

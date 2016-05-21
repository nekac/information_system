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
public class Demonstrator {

    private String userName;
    private String password;
    private String name;
    private String surname;
    private String telNumber;
    private String email;
    private String image;
    private String odsek;
    private int godina;
    private double prosek;

    public Demonstrator() {}

    public Demonstrator(String userName, String password, String name, String surname, String telNumber, String email, String image, String odsek, int godina, double prosek) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.telNumber = telNumber;
        this.email = email;
        this.image = image;
        this.odsek = odsek;
        this.godina = godina;
        this.prosek = prosek;
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

    public String getOdsek() {
        return odsek;
    }

    public int getGodina() {
        return godina;
    }

    public double getProsek() {
        return prosek;
    }

    public void setOdsek(String odsek) {
        this.odsek = odsek;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public void setProsek(double prosek) {
        this.prosek = prosek;
    }
    
    public static void addDemonstrator(String userName, String password, String name, String surname, String telNumber, String email, String image, String odsek, Integer godina, Double prosek, int valid) {
        DB.executeUpdate("insert into demonstrator (username, password, name, surname, phone_number, email, picture, odsek, godina, prosek)"
                + "values('" + userName + "', '" + password + "', '" + name + "', '" + surname + "', '"
                + telNumber + "', '" + email + "', '" + image + "', '" + odsek + "', '" + godina + "', '" + prosek + "')");
        DB.executeUpdate("insert into user (username, password, type, valid) values ('" + userName + "', '" + password + "', '2', '" + valid + "')");
    }
    
    public static void updateDemonstrator(String uusername, String ppassword) {
        DB.executeUpdate("update demonstrator set password = '" + ppassword + "' where username = '" + uusername + "'");
    }
    
    public static ResultSet citajDemonstratore() {
        ResultSet rs;
        rs = DB.executeQuery("select * from demonstrator");
        return rs;
    }
    
    public static Demonstrator citajDemosaUsername(String uusername) {
        ResultSet rs;
        rs = DB.executeQuery("select * from demonstrator where username = '" + uusername + "'");
        Demonstrator dem = new Demonstrator();
        try {
            while (rs.next()) {
                dem.setUserName(rs.getString("username"));
                dem.setPassword(rs.getString("password"));
                dem.setName(rs.getString("name"));
                dem.setSurname(rs.getString("surname"));
                dem.setTelNumber(rs.getString("phone_number"));
                dem.setEmail(rs.getString("email"));
                dem.setImage(rs.getString("picture"));
                dem.setOdsek(rs.getString("odsek"));
                dem.setGodina(rs.getInt("godina"));
                dem.setProsek(rs.getDouble("prosek"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Demonstrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dem;
    }
    
}

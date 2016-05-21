/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikola
 */
public class DB {
    
    private static DB instance = null;
    private static final int MAX_CON = 5;
    private Connection[] cons = new Connection[MAX_CON];

    private int free = MAX_CON, first, last;

    private DB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < MAX_CON; i++) {
                cons[i] = DriverManager.getConnection("jdbc:mysql://localhost:3306/informacioni_sistem", "root", "");
            }
            first = last = 0;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection ret = null;
        if (free == 0) {
            return ret;
        }

        free--;
        ret = cons[first];
        first = (first + 1) % MAX_CON;
        return ret;
    }

    public synchronized void putConnection(Connection c) {
        if (c == null || free == MAX_CON) {
            return;
        }
        free++;
        cons[last] = c;
        last = (last + 1) % MAX_CON;
    }
    
    public static int executeUpdate(String query){
        Connection c = DB.getInstance().getConnection();
        int key = -1;
        try {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.getInstance().putConnection(c);
        }
        return key;
    }
    
    public static ResultSet executeQuery(String query){
        Connection c = DB.getInstance().getConnection();
        ResultSet retValue = null;
        try {
            retValue = c.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.getInstance().putConnection(c);
        }
        return retValue;
    }
    
}

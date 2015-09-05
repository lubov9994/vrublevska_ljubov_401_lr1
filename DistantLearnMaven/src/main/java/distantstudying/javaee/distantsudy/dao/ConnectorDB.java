/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantstudying.javaee.distantsudy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Администратор
 */
public class ConnectorDB {
    private String dbname = "javaeechgu";
    private String dbhost = "localhost";
    private String dbport = "3306";
    private String user = "admin";
    private String pass = "admin";
    
    public ConnectorDB( String dbname, String dbhost, String dbport ) {
        this.dbname = dbname;
        this.dbhost = dbhost;
        this.dbport = dbport;
    }    

    public ConnectorDB( String dbname, String dbhost, String dbport, String user, String pass ) {
        this.dbname = dbname;
        this.dbhost = dbhost;
        this.dbport = dbport;
        this.user = user;
        this.pass = pass;
    }
    
    public ConnectorDB () {
    }

    public String getDbname() {
        return dbname;
    }

    public String getDbhost() {
        return dbhost;
    }

    public String getDbport() {
        return dbport;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public void setDbhost(String dbhost) {
        this.dbhost = dbhost;
    }

    public void setDbport(String dbport) {
        this.dbport = dbport;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Connection createConnection(){
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" +
                                                this.dbhost + ":" +
                                                this.dbport + "/"+
                                                this.dbname,
                                                this.user,
                                                this.pass);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }
    
}

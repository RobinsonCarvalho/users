package com.learning.users.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private String url;
    private Connection conn;
    private String userDb;
    private String passwordDb;

    public Connector(){
        url = "jdbc:mysql://localhost:3306/store";
        conn = null;
    }

    public Connection connectDatabase(String userDb, String passwordDb) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, userDb, passwordDb);
        }
        catch (ClassNotFoundException cnf){
            System.out.println("There was an issue loading the MySQL driver. " + cnf.getMessage());
        }
        catch (SQLException se){
            System.out.println("There was an issue connecting to database. " + se.getMessage());
        }
        finally {
            return conn;
        }
    }

}

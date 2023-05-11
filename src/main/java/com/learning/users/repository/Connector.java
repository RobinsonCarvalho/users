package com.learning.users.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private Connection conn;
    private final String url = "jdbc:mysql://localhost:3306/store";
    private final String userDb = "root";
    private final String passwordDb = "Administrator1983";

    public Connector(){

        conn = null;
    }

    public Connection connectDatabase() throws Exception{

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, userDb, passwordDb);
        return conn;

    }

}

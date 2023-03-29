package com.learning.users.model;

import java.sql.*;

public class ConnectionDBase {

    private String url;
    Connection conn;

    public ConnectionDBase(String url) {

        this.url = url;

    }

    public Connection connect() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(this.url);

    }
}

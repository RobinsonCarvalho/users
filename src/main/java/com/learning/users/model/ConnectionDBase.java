package com.learning.users.model;

import java.sql.*;

public class ConnectionDBase {

    private String url;

    public ConnectionDBase() {
        this.url = "jdbc:mysql://localhost:3306/store?user=root&password=Administrator1983";
    }

    public Connection connect() throws ClassNotFoundException, SQLException {

        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(this.url);
        return conn;
    }
}

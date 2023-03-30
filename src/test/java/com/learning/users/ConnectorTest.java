package com.learning.users;

import com.learning.users.repository.Connector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectorTest {

    private Connector connector;
    private Connection conn;
    private final String usr = "root";
    private String pass = "Administrator1983";

    @Test
    void shouldOpenConnectionToDatabase() {

        connector = new Connector();

        try{
            conn = connector.connectDatabase(usr, pass);
            Assertions.assertNotNull(conn);
            conn.close();
        }
        catch (SQLException se){
            se.printStackTrace();
        }

    }

    @Test
    void shouldCloseConnectionToDatabase() throws SQLException {

        connector = new Connector();
        conn = connector.connectDatabase(usr, pass);
        conn.close();
        Assertions.assertTrue(conn.isClosed());
    }

    @Test
    void shouldThrowSqlException() {

        pass = "";
        connector = new Connector();
        Assertions.assertNull(connector.connectDatabase(usr, pass));
    }

    @Test
    void shouldExecuteQueryOnDatabase() throws Exception {

        connector = new Connector();
        Statement statement;
        ResultSet resultSet;

        conn = connector.connectDatabase(usr, pass);
        statement = conn.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM USER LIMIT 1");
        Assertions.assertEquals(0, resultSet.getRow());
        conn.close();

    }

}
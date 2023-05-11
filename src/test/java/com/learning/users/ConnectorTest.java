package com.learning.users;

import com.learning.users.repository.Connector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectorTest {

    private Connector connector;
    private Connection conn;
    private PreparedStatement ps;

    @Test
    void shouldOpenConnectionToDatabase() {

        try{
            connector = new Connector();
            conn = connector.connectDatabase();
            Assertions.assertNotNull(conn);
            conn.close();
        }
        catch (SQLException se){
            se.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void shouldNotConnectionToDatabase() {

        try{
            connector = new Connector();
            conn = connector.connectDatabase();
            Assertions.assertNotNull(conn);
            conn.close();
        }
        catch (SQLException se){
            se.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    void shouldCloseConnectionToDatabase() {

        try{
            connector = new Connector();
            conn = connector.connectDatabase();
            conn.close();
            Assertions.assertTrue(conn.isClosed());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    void shouldThrowSqlException() {

        try{
            Assertions.assertThrows(NullPointerException.class,
                    ()->connector.connectDatabase());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
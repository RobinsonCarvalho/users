package com.learning.users;

import com.learning.users.model.ConnectionDBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDbaseTest {

    Connection conn = null;
    ConnectionDBase connectionDBase;

    @Test
    void shouldConnectToDatabase() throws Exception {

        String url = "jdbc:mysql://localhost:3306/store?user=root&password=Administrator1983";
        connectionDBase = new ConnectionDBase(url);
        conn = connectionDBase.connect();
        Assertions.assertNotNull(conn);

    }

    @Test
    void shouldThrowSQLExceptionWhenConnectionNull(){

        String url = "jdbc:mysql://localhost:3306/store?user=&password=";
        connectionDBase = new ConnectionDBase(url);

        Assertions.assertThrows(SQLException.class,
                ()-> connectionDBase.connect());

    }

}

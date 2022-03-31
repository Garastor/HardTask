package banking.db;

import java.sql.*;

public class ConnectToDb {

    public Connection doConnect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:/Users/tchornyi/Documents/MyJavaProjects/Simple Banking System/src/banking/resources/card.s3db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("SQL Error:\n" + e.getMessage());
        }
        return conn;
    }

}

package banking.db;

import java.sql.*;

public class ConnectToDb {

    public Connection doConnect(String url) {
        Connection conn = null;
        try {
            String newUrl = "jdbc:sqlite:/Users/tchornyi/Documents/MyJavaProjects/Battleship/Simple Banking System/Simple Banking System/task/src/banking/card.s3db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("SQL Error:\n" + e.getMessage());
        }
        return conn;
    }

        public void closeConnect (Connection conn){
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Connection is closed\n");
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error:\n" + ex.getMessage());
            }
        }

}

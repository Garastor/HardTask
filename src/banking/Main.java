package banking;

import banking.db.ConnectToDb;
import banking.session.Session;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        ConnectToDb connect = new ConnectToDb();
        Connection conn = connect.doConnect("jdbc:sqlite:"+args[1]);

        Session session = new Session(conn);
        session.sessionStart();

        conn.close();
        System.out.println("\nBye!");

    }
}

/*
4000003823193590
8814

4000009188452711
6536
 */


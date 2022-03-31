package banking;

import banking.db.ConnectToDb;
import banking.session.Session;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        Session session = new Session();
        session.sessionStart();

    }
}



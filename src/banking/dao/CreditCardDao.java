package banking.dao;

import banking.db.ConnectToDb;
import banking.entity.CreditCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDao {

    private final ConnectToDb connect = new ConnectToDb();
    private Connection conn;
    private PreparedStatement pst;
    private Statement st;
    private ResultSet rs;

    public CreditCardDao() {
    }

    public void create(CreditCard card) {
        try {
            conn = connect.doConnect();
            String sql = "INSERT INTO card (id, number, pin) VALUES(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, card.getId());
            pst.setString(2, card.getNumber());
            pst.setString(3, card.getPin());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Creating card is failed");
        } finally {
            try {
                pst.close();
            } catch (Exception e) { /* Ignored */ }
            try {
                conn.close();
            } catch (Exception e) { /* Ignored */ }
        }
    }

    public CreditCard read(String cardNumber, String pin) {
        CreditCard card = new CreditCard();
        try {
            conn = connect.doConnect();
            String sql = "SELECT * FROM card WHERE number=? AND pin=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, String.valueOf(cardNumber));
            pst.setString(2, String.valueOf(pin));
            rs = pst.executeQuery();
            rs.next();
            card.setId(rs.getInt(1));
            card.setNumber(rs.getString(2));
            card.setPin(rs.getString(3));
            card.setBalance(rs.getInt(4));
        } catch (SQLException ex) {
            System.out.println("ERROR: Reading card is failed");
            card = null;
        } finally {
            try {
                pst.close();
            } catch (Exception e) { /* Ignored */ }
            try {
                rs.close();
            } catch (Exception e) { /* Ignored */ }
            try {
                conn.close();
            } catch (Exception e) { /* Ignored */ }
        }
        return card;
    }

    public List<CreditCard> readAll() {
        List<CreditCard> cardList = new ArrayList<>();
        try {
            conn = connect.doConnect();
            String sql = "SELECT * FROM card";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                CreditCard card = new CreditCard();
                card.setId(rs.getInt(1));
                card.setNumber(rs.getString(2));
                card.setPin(rs.getString(3));
                card.setBalance(rs.getInt(4));
                cardList.add(card);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: Reading all cards is failed");
            System.out.println(ex.getMessage());
        } finally {
            try {
                pst.close();
            } catch (Exception e) { /* Ignored */ }
            try {
                rs.close();
            } catch (Exception e) { /* Ignored */ }
            try {
                conn.close();
            } catch (Exception e) { /* Ignored */ }
        }
        return cardList;
    }

    public void update(String cardNumber, String income, String operation) {
        try {
            conn = connect.doConnect();
            String sql = "UPDATE card SET balance=balance" + operation + "? WHERE number=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, income);
            pst.setString(2, cardNumber);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Updating card is failed");
        } finally {
            try {
                pst.close();
            } catch (Exception e) { /* Ignored */ }
            try {
                conn.close();
            } catch (Exception e) { /* Ignored */ }
        }
    }

    public void delete(CreditCard card) {
        try {
            conn = connect.doConnect();
            String sql = "DELETE FROM card WHERE number=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, card.getNumber());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Deleting card is failed");
        } finally {
            try {
                pst.close();
            } catch (Exception e) { /* Ignored */ }
            try {
                conn.close();
            } catch (Exception e) { /* Ignored */ }
        }
    }

    //IF NOT EXISTS
    public void createTable() {
        try {
            conn = connect.doConnect();
            String tableSql = "CREATE TABLE card( " +
                    "id INTEGER, " +
                    "number TEXT, " +
                    "pin TEXT, " +
                    "balance INTEGER DEFAULT 0)";
            st = conn.createStatement();
            st.executeUpdate(tableSql);
            System.out.println("Table is created");
        } catch (SQLException ex) {
            System.out.println("ERROR: Creating table \"card\" is failed");
        } finally {
            try {
                st.close();
            } catch (Exception e) { /* Ignored */ }
            try {
                conn.close();
            } catch (Exception e) { /* Ignored */ }
        }
    }

    public void deleteTable() {
        try {
            conn = connect.doConnect();
            String sql = "DROP TABLE IF EXISTS card";
            st = conn.createStatement();
            st.executeUpdate(sql);
            System.out.println("Table is deleted");
        } catch (SQLException ex) {
            System.out.println("ERROR: Can't delete table");
            System.out.println(ex.getMessage());
        } finally {
            try {
                pst.close();
            } catch (Exception e) { /* Ignored */ }
            try {
                conn.close();
            } catch (Exception e) { /* Ignored */ }
        }
    }
}


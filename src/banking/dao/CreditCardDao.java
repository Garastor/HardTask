package banking.dao;

import banking.entity.CreditCard;

import java.sql.*;
import java.util.ArrayList;

public class CreditCardDao {

    private Connection conn;
    private PreparedStatement pst;
    private Statement st;

    public CreditCardDao (Connection conn) {
        this.conn = conn;
    }

    public void create(CreditCard card) {
        if(conn != null) {
            try {
                String sql = "INSERT INTO card (id, number, pin) VALUES(?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, card.getId());
                pst.setString(2, card.getNumber());
                pst.setString(3, card.getPin());
                pst.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("ERROR: Creating card is failed");
            }
        }else System.out.println("create: connect is null");
    }

    public CreditCard read(String cardNumber, String pin){
        CreditCard card = new CreditCard();
        if(conn != null) {
            try {
                String sql = "SELECT * FROM card WHERE number=? AND pin=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, String.valueOf(cardNumber));
                pst.setString(2, String.valueOf(pin));
                ResultSet rs = pst.executeQuery();
                rs.next();
                card.setId(rs.getInt(1));
                card.setNumber(rs.getString(2));
                card.setPin(rs.getString(3));
                card.setBalance(rs.getInt(4));
            } catch (SQLException ex) {
                System.out.println("ERROR: Reading card is failed");
                card = null;
            }
        } else System.out.println("read: connect is null");
        return card;
    }

    public ArrayList<CreditCard> readAll () {
        ArrayList<CreditCard> cardList = new ArrayList<>();
        if (conn != null) {
            try {
                String sql = "SELECT * FROM card";
                pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
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
            }
        } else System.out.println("readAll: connect is null");
        return cardList;
    }

    public void update(String cardNumber, String income, String operation) {
        if(conn != null) {
            try {
                String sql = "UPDATE card SET balance=balance"+operation+"? WHERE number=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, income);
                pst.setString(2, cardNumber);
                pst.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("ERROR: Updating card is failed");
            }
        }
    }

    public void delete(CreditCard card) {
        if(conn != null) {
            try {
                String sql = "DELETE FROM card WHERE number=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, card.getNumber());
                pst.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("ERROR: Deleting card is failed");
            }
        }
    }

//IF NOT EXISTS
    public void createTable () {
        if (conn != null) {
            try {
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
            }
        }
    }

    public void deleteTable() {
        if(conn != null) {
            try {
                String sql = "DROP TABLE IF EXISTS card";
                st = conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Table is deleted");
            } catch (SQLException ex) {
                System.out.println("ERROR: Can't delete table");
                System.out.println(ex.getMessage());
            }
        }
    }
}


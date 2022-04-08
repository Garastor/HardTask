package banking.dao;

import banking.db.ConnectToDb;
import banking.entity.CreditCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static banking.entity.Constants.*;

public class CreditCardDao {

    private final ConnectToDb connect = new ConnectToDb();

    public void create(CreditCard card) {
        try (Connection conn = connect.doConnect(); PreparedStatement pst = createPstCardCreate(card, conn)) {
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Creating card is failed");
        }
    }

    public CreditCard read(String cardNumber, String pin) {
        CreditCard card = new CreditCard();
        try (Connection conn = connect.doConnect(); PreparedStatement pst = createPstCardRead(cardNumber, pin, conn);
             ResultSet rs = pst.executeQuery()) {
            card.setId(rs.getInt(1));
            card.setNumber(rs.getString(2));
            card.setPin(rs.getString(3));
            card.setBalance(rs.getInt(4));
        } catch (SQLException ex) {
            System.out.println("ERROR: Reading card is failed");
        }
        return card;
    }

    public List<CreditCard> readAll() {
        List<CreditCard> cardList = new ArrayList<>();
        try (Connection conn = connect.doConnect(); PreparedStatement pst = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = pst.executeQuery()) {
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
        return cardList;
    }

    public void update(String cardNumber, String income, String operation) {
        String str = String.format(SQL_UPDATE, operation);
        try (Connection conn = connect.doConnect();
             PreparedStatement pst = createPstCardUpdate(cardNumber, income, conn, str)) {
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Updating card is failed");
        }
    }

    public void delete(CreditCard card) {
        try (Connection conn = connect.doConnect();
             PreparedStatement pst = createPstCardDelete(card, conn)) {
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Deleting card is failed");
        }
    }


    private PreparedStatement createPstCardDelete(CreditCard card, Connection conn) throws SQLException {
        PreparedStatement pst = conn.prepareStatement(SQL_DELETE);
        pst.setString(1, card.getNumber());
        return pst;
    }

    private PreparedStatement createPstCardRead(String cardNumber, String pin, Connection conn) throws SQLException {
        PreparedStatement pst = conn.prepareStatement(SQL_SELECT_ONE);
        pst.setString(1, String.valueOf(cardNumber));
        pst.setString(2, String.valueOf(pin));
        return pst;
    }

    private PreparedStatement createPstCardCreate(CreditCard card, Connection conn) throws SQLException {
        PreparedStatement pst = conn.prepareStatement(SQL_INSERT);
        pst.setInt(1, card.getId());
        pst.setString(2, card.getNumber());
        pst.setString(3, card.getPin());
        return pst;
    }

    private PreparedStatement createPstCardUpdate(String cardNumber, String income, Connection conn, String str) throws SQLException {
        PreparedStatement pst = conn.prepareStatement(str);
        pst.setString(1, income);
        pst.setString(2, cardNumber);
        return pst;
    }

}


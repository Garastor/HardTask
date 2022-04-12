package banking.entity;

public final class Constants {

    private Constants() {
    }

    public static final String ZERO = "0";
    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String THREE = "3";
    public static final String FOUR = "4";
    public static final String FIVE = "5";
    public static final String SQL_INSERT = "INSERT INTO card (id, number, pin) VALUES(?,?,?)";
    public static final String SQL_SELECT_ONE = "SELECT * FROM card WHERE number=? AND pin=?";
    public static final String SQL_SELECT_ALL = "SELECT * FROM card";
    public static final String SQL_UPDATE = "UPDATE card SET balance=balance %s ? WHERE number=?";
    public static final String SQL_DELETE = "DELETE FROM card WHERE number=?";

}

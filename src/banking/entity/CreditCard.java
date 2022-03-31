package banking.entity;

public class CreditCard {

    private int id;
    private String number;
    private String pin;
    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Your card number:\n" +
                getNumber() +
                "\nYour card PIN:\n" +
                getPin() + "\n";
    }
}

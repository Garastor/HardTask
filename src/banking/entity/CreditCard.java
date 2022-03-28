package banking.entity;

public class CreditCard {

    private int id;
    private long number;
    private int pin;
    private long balance;

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", number=" + number +
                ", pin=" + pin +
                '}';
    }
}

package banking.entity;

public class Account {

    private int id;
    private CreditCard creditCard;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "Your card number:\n" + creditCard.getNumber() +
                "\nYour card PIN:\n" + creditCard.getPin() +
                "\n";
    }
}

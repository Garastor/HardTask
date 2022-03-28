package banking.dao;

import banking.db.Connect;
import banking.entity.CreditCard;

import java.util.ArrayList;
import java.util.List;

public class CreditCardDao {

    Connect connect = new Connect();

    public int create(CreditCard creditCard) {
        creditCard.setId(creditCards.size());
        creditCards.add(creditCard);
        return creditCard.getId();
    }

    public CreditCard read(int id){
        return creditCards.get(id);
    }

    public void update(CreditCard creditCard) {
        creditCards.set(creditCard.getId(), creditCard);
    }

    public void delete(CreditCard creditCard) {
        creditCards.remove(creditCard.getId());
    }
}


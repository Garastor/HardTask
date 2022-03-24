package banking.dao;

import banking.entity.Account;
import banking.entity.CreditCard;

import java.util.ArrayList;
import java.util.List;

public class CreditCardDao {

    private final List<CreditCard> creditCards = new ArrayList<>();

    public void create(CreditCard creditCard) {
        creditCards.add(creditCard);
    }

    public CreditCard read(int id){
        CreditCard creditCard = new CreditCard();
        for (CreditCard card: creditCards) {
            if (card.getId() == id){
                creditCard = card;
            }
        }
        return creditCard;
    }

    public void update(CreditCard creditCard) {
        creditCards.set(creditCard.getId(), creditCard);
    }

    public void delete(CreditCard creditCard) {
        creditCards.remove(creditCard.getId());
    }
}


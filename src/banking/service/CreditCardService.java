package banking.service;

import banking.dao.AccountDao;
import banking.dao.CreditCardDao;
import banking.entity.Account;
import banking.entity.CreditCard;

import java.util.Random;

public class CreditCardService {

    private final CreditCardDao creditCardDao = new CreditCardDao();

    public CreditCard findCard(int id) {
        return creditCardDao.read(id);
    }

    public void saveCard(CreditCard creditCard) {

        creditCardDao.create(creditCard);
    }

    public void deleteCard(CreditCard creditCard) {
        creditCardDao.delete(creditCard);
    }

    public void updateCard(CreditCard creditCard) {
        creditCardDao.update(creditCard);
    }

    public CreditCard createCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(Long.parseLong(randomizer(new StringBuilder("400000"), 10)));
        creditCard.setPin(Integer.parseInt(randomizer(new StringBuilder(""), 4)));
        creditCard.setBalance(0);
        return creditCard;
    }

    String randomizer(StringBuilder stringBuilder, int cells) {
        Random random = new Random();
        for (int n = 0; n < cells; n++) {
            stringBuilder.append(random.nextInt(9) + 1);
        }
        return stringBuilder.toString();
    }

}

package banking.service;

import banking.dao.CreditCardDao;
import banking.entity.CreditCard;

import java.util.Random;

public class CreditCardService {

    private final CreditCardDao creditCardDao = new CreditCardDao();

    public CreditCard findCard(int id) {
        return creditCardDao.read(id);
    }

    public CreditCard saveCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(addCheckSum(randomizer("400000", 9)));
        creditCard.setPin(Integer.parseInt(randomizer("", 4)));
        creditCard.setBalance(0);
        int id = creditCardDao.create(creditCard);
        creditCard.setId(id);
        return creditCard;
    }

    public void deleteCard(CreditCard creditCard) {
        creditCardDao.delete(creditCard);
    }

    public void updateCard(CreditCard creditCard) {
        creditCardDao.update(creditCard);
    }

    String randomizer(String firstSymbols, int createCells) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(firstSymbols);
        for (int n = 0; n < createCells; n++) {
            stringBuilder.append(random.nextInt(9) + 1);
        }
        return stringBuilder.toString();
    }

    long addCheckSum(String randomNumber) {
        int sum = 0;
        for (int i=randomNumber.length()-1; i>=0; i--){
            int a = Integer.parseInt(String.valueOf(randomNumber.charAt(i)));
            if(i % 2 == 0) {
                a *= 2;
                if (a > 9) a -= 9;
            }
            sum += a;
        }
        return Long.parseLong(randomNumber.concat(String.valueOf((sum*9)%10)));
    }

}

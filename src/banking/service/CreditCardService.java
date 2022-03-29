package banking.service;

import banking.entity.CreditCard;

import java.util.Optional;
import java.util.Random;

public class CreditCardService {

    public CreditCard createCard() {
        CreditCard card = new CreditCard();
        card.setId(Integer.parseInt(randomizer("", 2)));
        String number = randomizer("400000", 9);
        card.setNumber(number.concat(takeCheckSum(number)));
        card.setPin(randomizer("", 4));
        card.setBalance(0);
        System.out.println(card);
        return card;
    }

    public boolean compareCheckSum(String number) {
        String checkSum = String.valueOf(number.charAt(number.length() - 1));
        String checkNumb = Optional.ofNullable(number)
                .filter(str -> str.length() != 0)
                .map(str -> str.substring(0, str.length() - 1))
                .orElse(number);
        return (checkSum.equals(takeCheckSum(checkNumb)));
    }

    private String randomizer(String firstSymbols, int createCells) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(firstSymbols);
        for (int n = 0; n < createCells; n++) {
            stringBuilder.append(random.nextInt(9) + 1);
        }
        return stringBuilder.toString();
    }

    private String takeCheckSum(String randomNumber) {
        int sum = 0;
        for (int i = randomNumber.length() - 1; i >= 0; i--) {
            int a = Integer.parseInt(String.valueOf(randomNumber.charAt(i)));
            if (i % 2 == 0) {
                a *= 2;
                if (a > 9) a -= 9;
            }
            sum += a;
        }
        return String.valueOf((sum * 9) % 10);
    }

}

package banking.service;

import banking.entity.CreditCard;

import java.util.Random;

public class CreditCardService {

    public CreditCard createCard() {
        CreditCard card = new CreditCard();
        card.setNumber(addCheckSum(randomizer("400000", 9)));
        card.setPin(randomizer("", 4));
        card.setBalance(0);
        System.out.println(card);
        return card;
    }

    String randomizer(String firstSymbols, int createCells) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(firstSymbols);
        for (int n = 0; n < createCells; n++) {
            stringBuilder.append(random.nextInt(9) + 1);
        }
        return stringBuilder.toString();
    }

    String addCheckSum(String randomNumber) {
        int sum = 0;
        for (int i=randomNumber.length()-1; i>=0; i--){
            int a = Integer.parseInt(String.valueOf(randomNumber.charAt(i)));
            if(i % 2 == 0) {
                a *= 2;
                if (a > 9) a -= 9;
            }
            sum += a;
        }
        return randomNumber.concat(String.valueOf((sum*9)%10));
    }

}

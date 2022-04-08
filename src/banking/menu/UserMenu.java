package banking.menu;

import banking.dao.CreditCardDao;
import banking.entity.CreditCard;
import banking.entity.InputFromKeyboard;
import banking.entity.PrintMessage;
import banking.service.CreditCardService;

import java.util.Objects;

import static banking.entity.Constants.*;

public class UserMenu {

    private final CreditCardDao creditCardDao;
    private final CreditCardService creditCardService;
    private CreditCard card;

    public UserMenu(String cardNumber, String pin) {
        creditCardDao = new CreditCardDao();
        creditCardService = new CreditCardService();
        card = creditCardDao.read(cardNumber, pin);
    }

    public boolean startMenu() {
        PrintMessage.USER_MENU.print();
        String input = InputFromKeyboard.USER_MENU.get();
        if (Objects.equals(input, ONE)) {
            getBalance();
        } else if (Objects.equals(input, TWO)) {
            addIncome();
        } else if (Objects.equals(input, THREE)) {
            doTransfer();
        } else if (Objects.equals(input, FOUR)) {
            deleteCard();
            return false;
        } else if (Objects.equals(input, FIVE)) {
            PrintMessage.LOGOUT.print();
            return false;
        }
        return !Objects.equals(input, ZERO);
    }

    private void getBalance() {
        PrintMessage.BALANCE.print();
        System.out.println(card.getBalance());
    }

    private void addIncome() {
        PrintMessage.ENTER_INCOME.print();
        creditCardDao.update(card.getNumber(), InputFromKeyboard.DIGITS.get(), "+");
        PrintMessage.INCOME_ADDED.print();
        card = creditCardDao.read(card.getNumber(), card.getPin());
    }

    private void doTransfer() {
        PrintMessage.DO_TRANSFER.print();
        String cardNumber = InputFromKeyboard.CARD_NUMBER.get();
        if (isCardChecked(cardNumber)) {
            PrintMessage.TRANS_HOW_MUCH.print();
            String money = InputFromKeyboard.DIGITS.get();
            if (card.getBalance() < Integer.parseInt(money)) {
                PrintMessage.TRANS_NO_ENOUGH.print();
            } else {
                creditCardDao.update(cardNumber, money, "+");
                creditCardDao.update(card.getNumber(), (money), "-");
                card = creditCardDao.read(card.getNumber(), card.getPin());
            }
        }
    }

    private void deleteCard() {
        creditCardDao.delete(card);
        PrintMessage.CLOSE_ACC.print();
    }

    private boolean isCardChecked(String cardNumber) {
        if (!creditCardService.compareCheckSum(cardNumber)) {
            PrintMessage.TRANS_MISTAKE.print();
            return false;
        } else if (!isFindCard(cardNumber)) {
            PrintMessage.TRANS_NO_CARD.print();
            return false;
        }
        return true;
    }

    private boolean isFindCard(String cardNumber) {
        for (CreditCard creditCard : creditCardDao.readAll()) {
            if (Objects.equals(creditCard.getNumber(), cardNumber)) {
                return true;
            }
        }
        return false;
    }

}

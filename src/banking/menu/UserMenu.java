package banking.menu;

import banking.dao.CreditCardDao;
import banking.entity.CreditCard;
import banking.entity.Inputs;
import banking.entity.PrintMessage;
import banking.service.CreditCardService;

import java.util.Objects;

public class UserMenu {

    private CreditCardDao creditCardDao;
    private CreditCardService creditCardService;
    private CreditCard card;

    public UserMenu(String cardNumber, String pin) {
        creditCardDao = new CreditCardDao();
        creditCardService = new CreditCardService();
        card = creditCardDao.read(cardNumber, pin);
    }

    public boolean startMenu() {
        PrintMessage.MENU.print();
        String input = Inputs.MENU2.get();
        if (Objects.equals(input, "1")) {
            getBalance();
        } else if (Objects.equals(input, "2")) {
            addIncome();
        } else if (Objects.equals(input, "3")) {
            doTransfer();
        } else if (Objects.equals(input, "4")) {
            deleteCard();
            return false;
        } else if (Objects.equals(input, "5")) {
            PrintMessage.LOGOUT.print();
            return false;
        }
        return !Objects.equals(input, "0");
    }

    private void getBalance() {
        PrintMessage.BALANCE.print();
        System.out.println(card.getBalance());
    }

    private void addIncome() {
        PrintMessage.ENTERINCOME.print();
        creditCardDao.update(card.getNumber(), Inputs.DIGITS.get(), "+");
        PrintMessage.INCOMEADDED.print();
        card = creditCardDao.read(card.getNumber(), card.getPin());
    }

    private void doTransfer() {
        PrintMessage.DOTRANSFER.print();
        String cardNumber = Inputs.NUMBER.get();
        if (isCardChecked(cardNumber)) {
            PrintMessage.TRANSHOWMUCH.print();
            String money = Inputs.DIGITS.get();
            if (card.getBalance() < Integer.parseInt(money)) {
                PrintMessage.TRANSNOENOUGH.print();
            } else {
                creditCardDao.update(cardNumber, money, "+");
                creditCardDao.update(card.getNumber(), (money), "-");
                card = creditCardDao.read(card.getNumber(), card.getPin());
            }
        }
    }

    private void deleteCard() {
        creditCardDao.delete(card);
        PrintMessage.CLOSEACC.print();
    }

    private boolean isCardChecked(String cardNumber) {
        if (!creditCardService.compareCheckSum(cardNumber)) {
            PrintMessage.TRANSMISTAKE.print();
            return false;
        } else if (!isFindCard(cardNumber)) {
            PrintMessage.TRANSNOCARD.print();
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

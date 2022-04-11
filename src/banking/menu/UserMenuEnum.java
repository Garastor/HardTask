package banking.menu;

import banking.dao.CreditCardDao;
import banking.entity.Constants;
import banking.entity.CreditCard;
import banking.entity.InputFromKeyboard;
import banking.entity.PrintMessage;
import banking.service.CreditCardService;

import java.util.Objects;

public enum UserMenuEnum {

    GET_BALANCE(Constants.ONE) {
        @Override
        public boolean menuOption(String cardNumber, String pin) {
            CreditCard card = creditCardDao.read(cardNumber, pin);
            PrintMessage.BALANCE.print();
            System.out.println(card.getBalance());
            return true;
        }
    },
    ADD_INCOME(Constants.TWO) {
        @Override
        public boolean menuOption(String cardNumber, String pin) {
            CreditCard card = creditCardDao.read(cardNumber, pin);
            PrintMessage.ENTER_INCOME.print();
            creditCardDao.update(card.getNumber(), InputFromKeyboard.DIGITS.get(), "+");
            PrintMessage.INCOME_ADDED.print();
            return true;
        }
    },
    DO_TRANSFER(Constants.THREE) {
        @Override
        public boolean menuOption(String cardNumber, String pin) {
            CreditCard card = creditCardDao.read(cardNumber, pin);
            PrintMessage.DO_TRANSFER.print();
            String secondCardNumber = InputFromKeyboard.CARD_NUMBER.get();
            if (isCardChecked(secondCardNumber)) {
                PrintMessage.TRANS_HOW_MUCH.print();
                String money = InputFromKeyboard.DIGITS.get();
                if (card.getBalance() < Integer.parseInt(money)) {
                    PrintMessage.TRANS_NO_ENOUGH.print();
                } else {
                    creditCardDao.update(secondCardNumber, money, "+");
                    creditCardDao.update(card.getNumber(), (money), "-");
                }
            }
            return true;
        }
    },
    DELETE_CARD(Constants.FOUR) {
        @Override
        public boolean menuOption(String cardNumber, String pin) {
            creditCardDao.delete(creditCardDao.read(cardNumber, pin));
            PrintMessage.CLOSE_ACC.print();
            return false;
        }
    },
    CLOSE_MENU(Constants.FIVE) {
        @Override
        public boolean menuOption(String cardNumber, String pin) {
            return false;
        }
    };

    private final String input;

    UserMenuEnum(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public abstract boolean menuOption(String cardNumber, String pin);

    private static final CreditCardDao creditCardDao = new CreditCardDao();
    private static final CreditCardService creditCardService = new CreditCardService();

    private static boolean isCardChecked(String cardNumber) {
        if (!creditCardService.compareCheckSum(cardNumber)) {
            PrintMessage.TRANS_MISTAKE.print();
            return false;
        } else if (!isFindCard(cardNumber)) {
            PrintMessage.TRANS_NO_CARD.print();
            return false;
        }
        return true;
    }

    private static boolean isFindCard(String cardNumber) {
        for (CreditCard creditCard : creditCardDao.readAll()) {
            if (Objects.equals(creditCard.getNumber(), cardNumber)) {
                return true;
            }
        }
        return false;
    }

}



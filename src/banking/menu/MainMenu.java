package banking.menu;

import banking.dao.CreditCardDao;
import banking.entity.CreditCard;
import banking.entity.InputFromKeyboard;
import banking.entity.PrintMessage;
import banking.service.CreditCardService;

import java.util.Objects;

import static banking.entity.Constants.*;

public class MainMenu {

    private final CreditCardService creditCardService;
    private final CreditCardDao creditCardDao;
    private UserMenu userMenu;
    boolean userLoggedIn;

    public MainMenu() {
        creditCardService = new CreditCardService();
        creditCardDao = new CreditCardDao();
    }

    public void startMenu() {
        String input = TWO;
        do {
            if (userLoggedIn) {
                userLoggedIn = userMenu.startMenu();
            } else {
                PrintMessage.MAIN_MENU.print();
                input = InputFromKeyboard.MAIN_MENU.get();
                if (Objects.equals(input, ONE)) {
                    createNewCard();
                } else if (Objects.equals(input, TWO)) {
                    logIn();
                }
            }
        } while (!Objects.equals(input, ZERO));
        PrintMessage.BYE.print();
    }

    private void createNewCard() {
        creditCardDao.create(creditCardService.createCard());
    }

    private void logIn() {
        PrintMessage.ENTER_CARD.print();
        String cardNumber = InputFromKeyboard.CARD_NUMBER.get();
        PrintMessage.ENTER_PIN.print();
        String pin = InputFromKeyboard.CARD_PIN.get();
        if (isLogIn(cardNumber, pin)) {
            PrintMessage.LOGIN.print();
            userMenu = new UserMenu(cardNumber, pin);
            userLoggedIn = true;
        } else {
            PrintMessage.ERROR_LOGIN.print();
        }
    }

    private boolean isLogIn(String cardNumber, String pin) {
        for (CreditCard creditCard : creditCardDao.readAll()) {
            if ((Objects.equals(creditCard.getNumber(), cardNumber) && Objects.equals(creditCard.getPin(), pin))) {
                return true;
            }
        }
        return false;
    }

}

package banking.menu;

import banking.dao.CreditCardDao;
import banking.entity.CreditCard;
import banking.entity.Inputs;
import banking.entity.PrintMessage;
import banking.service.CreditCardService;

import java.util.Objects;

public class MainMenu {

    private CreditCardService creditCardService;
    private CreditCardDao creditCardDao;
    private UserMenu userMenu;
    boolean userLoggedIn;

    public MainMenu() {
        creditCardService = new CreditCardService();
        creditCardDao = new CreditCardDao();
    }

    public void startMenu() {
        String input = "2";
        do {
            if (userLoggedIn) {
                userLoggedIn = userMenu.startMenu();
            } else {
                PrintMessage.HELLO.print();
                input = Inputs.MENU.get();
                if (Objects.equals(input, "1")) {
                    createNewCard();
                } else if (Objects.equals(input, "2")) {
                    logIn();
                }
            }
        } while (!Objects.equals(input, "0"));
        PrintMessage.BYE.print();
    }

    private void createNewCard() {
        creditCardDao.create(creditCardService.createCard());
    }

    private void logIn() {
        PrintMessage.ENTERCARD.print();
        String cardNumber = Inputs.NUMBER.get();
        PrintMessage.ENTERPIN.print();
        String pin = Inputs.PIN.get();
        if (isLogIn(cardNumber, pin)) {
            PrintMessage.LOGIN.print();
            userMenu = new UserMenu(cardNumber, pin);
            userLoggedIn = true;
        } else {
            PrintMessage.ERRORLOGIN.print();
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

package banking.session;

import banking.dao.CreditCardDao;
import banking.entity.CreditCard;
import banking.entity.RegEx;
import banking.entity.Messages;
import banking.service.CreditCardService;

import java.util.Objects;
import java.util.Scanner;

public class Session {

    private final Scanner scanner = new Scanner(System.in);
    CreditCardService creditCardService;
    CreditCardDao creditCardDao;

    public Session() {
        this.creditCardDao = new CreditCardDao();
        this.creditCardService = new CreditCardService();
    }

    void printMessage(Messages messages) {
        System.out.println(messages.getMessage());
    }

    String takeInput(RegEx regEx) {
        while (true) {
            while (!scanner.hasNext()) {
                scanner.nextLine();
                scanner.next();
            }
            String s = scanner.next();
            if (regEx.check(s)) {
                return s;
            }
        }
    }

    public void sessionStart() {
        String input;
        //creditCardDao.createTable();
        do {
            printMessage(Messages.HELLO);
            input = takeInput(RegEx.MENU);
            switch (input) {
                case "1":
                    printMessage(Messages.CREATE);
                    creditCardDao.create(creditCardService.createCard());
                    break;
                case "2":
                    printMessage(Messages.ENTERCARD);
                    input = scanner.next();
                    String cardNumber = input;
                    printMessage(Messages.ENTERPIN);
                    input = scanner.next();
                    String pin = input;
                    if (isLogIn(cardNumber, pin)) {
                        printMessage(Messages.LOGIN);
                        CreditCard card = creditCardDao.read(cardNumber, pin);
                        input = accountMenu(card);
                    } else {
                        printMessage(Messages.ERRORLOGIN);
                    }
                    break;
            }
        } while (!Objects.equals(input, "0"));
        printMessage(Messages.BYE);
        //creditCardDao.deleteTable();
    }

    private String accountMenu(CreditCard card) {
        String input = "4";
        boolean accountMenu = true;
        while (accountMenu) {
            printMessage(Messages.MENU);
            input = takeInput(RegEx.MENU2);
            switch (input) {
                case "1":
                    printMessage(Messages.BALANCE);
                    System.out.println(card.getBalance());
                    break;
                case "2":
                    //add income
                    printMessage(Messages.ENTERINCOME);
                    creditCardDao.update(card.getNumber(), scanner.next(), "+");
                    printMessage(Messages.INCOMEADDED);
                    card = creditCardDao.read(card.getNumber(), card.getPin());
                    break;
                case "3":
                    //do transfer
                    printMessage(Messages.DOTRANSFER);
                    input = scanner.next();
                    if (!creditCardService.compareCheckSum(input)) {
                        printMessage(Messages.TRANSMISTAKE);
                        break;
                    }
                    if (!isFindCard(input)) {
                        printMessage(Messages.TRANSNOCARD);
                        break;
                    }
                    String cardNumber = input;
                    printMessage(Messages.TRANSHOWMUCH);
                    input = scanner.next();
                    if (card.getBalance() < Integer.parseInt(input)) {
                        printMessage(Messages.TRANSNOENOUGH);
                        break;
                    }
                    creditCardDao.update(cardNumber, input, "+");
                    creditCardDao.update(card.getNumber(), (input), "-");
                    card = creditCardDao.read(card.getNumber(), card.getPin());
                    break;
                case "4":
                    creditCardDao.delete(card);
                    printMessage(Messages.CLOSEACC);
                    accountMenu = false;
                    break;
                case "5":
                    printMessage(Messages.LOGOUT);
                    accountMenu = false;
                    break;
                case "0":
                    accountMenu = false;
                    break;
            }
        }
        return input;
    }

    boolean isLogIn(String cardNumber, String pin) {
        boolean login = false;
        for (CreditCard creditCard : creditCardDao.readAll()) {
            if ((Objects.equals(creditCard.getNumber(), cardNumber) && Objects.equals(creditCard.getPin(), pin))) {
                login = true;
                break;
            }
        }
        return login;
    }

    boolean isFindCard(String cardNumber) {
        boolean find = false;
        for (CreditCard creditCard : creditCardDao.readAll()) {
            if (Objects.equals(creditCard.getNumber(), cardNumber)) {
                find = true;
            }
        }
        return find;
    }

}

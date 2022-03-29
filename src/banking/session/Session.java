package banking.session;

import banking.dao.CreditCardDao;
import banking.entity.CreditCard;
import banking.service.CreditCardService;
import banking.res.Inputs;
import banking.res.Messages;

import java.sql.Connection;
import java.util.Objects;
import java.util.Scanner;

public class Session {

    private Scanner scanner = new Scanner(System.in);
    CreditCardService creditCardService;
    CreditCardDao creditCardDao;

    public Session(Connection conn) {
        this.creditCardDao = new CreditCardDao(conn);
        this.creditCardService = new CreditCardService();
    }


    void printMessage(Messages messages) {
        System.out.println(messages.getMessage());
    }

    String takeInput(Inputs inputs) {
        while (true) {
            while (!scanner.hasNext()) {
                scanner.nextLine();
                scanner.next();
            }
            String s = scanner.next();
            if (inputs.check(s)) {
                return s;
            }
        }
    }

    public void sessionStart () {
        String input;
        creditCardDao.createTable();
        do{
            printMessage(Messages.HELLO);
            input = takeInput(Inputs.MENU);
            switch (input) {
                case "1":
                    printMessage(Messages.CREATE);
                    CreditCard cd = creditCardService.createCard();
                    creditCardDao.create(cd);
                    break;
                case "2":
                    printMessage(Messages.ENTERCARD);
                    input = scanner.next();
                    String cardNumber = input;
                    printMessage(Messages.ENTERPIN);
                    input = scanner.next();
                    String pin = input;
                    if (isFindCard(cardNumber, pin)){
                        printMessage(Messages.LOGIN);
                        CreditCard card = creditCardDao.read(cardNumber, pin);
                        input = accountMenu(card);
                    } else {
                        printMessage(Messages.ERRORLOGIN);
                    }
                    break;
            }
        } while (!Objects.equals(input, "0"));
    }

    String accountMenu (CreditCard card) {
        String input = "4";
        boolean accountMenu = true;
        while (accountMenu){
            printMessage(Messages.MENU);
            input = takeInput(Inputs.MENU);
            switch (input) {
                case "1":
                    printMessage(Messages.BALANCE);
                    System.out.println(card.getBalance());
                    break;
                case "2":
                    printMessage(Messages.LOGOUT);
                    accountMenu = false;
                    break;
                case "0":
                    accountMenu=false;
                    break;
            }
        }
        return input;
    }

    boolean isFindCard(String cardNumber, String pin) {
        boolean find = false;
        for (CreditCard creditCard : creditCardDao.readAll()) {
            find = (Objects.equals(creditCard.getNumber(), cardNumber) && Objects.equals(creditCard.getPin(), pin));
            }
        return find;
    }


}

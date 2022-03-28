package banking.session;

import banking.entity.Account;
import banking.service.AccountService;
import banking.service.Inputs;
import banking.service.Messages;

import java.util.Scanner;

public class Session {

    private final AccountService accountService = new AccountService();
    Scanner scanner = new Scanner(System.in);

    public void sessionStart() {
        mainMenu();
    }

    void printMessage(Messages messages) {
        System.out.println(messages.getMessage());
    }

    String takeInput (Inputs inputs){
        String input;
        while (true){
            String s = scanner.nextLine();
            if(inputs.check(s)) {
                input = s;
                break;
            }
        }
        return input;
    }

    void mainMenu () {
        printMessage(Messages.HELLO);
        boolean mainMenu = true;
        while (mainMenu) {
            String input = takeInput(Inputs.MENU);
            switch (input) {
                case "0":
                    printMessage(Messages.BYE);
                    mainMenu = false;
                    break;
                case "1":
                    printMessage(Messages.CREATE);
                    accountService.saveAccount();
                    printMessage(Messages.HELLO);
                    break;
                case "2":
                    if(!accountMenu()){
                        printMessage(Messages.BYE);
                        mainMenu = false;
                    }
                    break;
            }
        }
    }

    boolean accountMenu () {
        boolean mainMenu = true;
        boolean accountMenu = true;
        printMessage(Messages.ENTERCARD);
        long cardNumber = Long.parseLong(takeInput(Inputs.NUMBER));
        printMessage(Messages.ENTERPIN);
        int pin = Integer.parseInt(takeInput(Inputs.PIN));
        int id = accountService.loginAccount(cardNumber, pin);
        if(id != -1) {
            Account account = accountService.findAccount(id);
            printMessage(Messages.LOGIN);
            while (accountMenu){
                printMessage(Messages.MENU);
                String input2 = takeInput(Inputs.MENU);
                switch (input2){
                    case "0":
                        mainMenu = false;
                        accountMenu = false;
                        break;
                    case "1":
                        printMessage(Messages.BALANCE);
                        System.out.println(account.getCreditCard().getBalance());
                        break;
                    case "2":
                        printMessage(Messages.LOGOUT);
                        printMessage(Messages.HELLO);
                        accountMenu = false;
                        break;
                }
            }
        } else {
            printMessage(Messages.ERRORLOGIN);
            printMessage(Messages.HELLO);
            mainMenu = true;
        }
        return mainMenu;
    }



}
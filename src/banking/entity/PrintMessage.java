package banking.entity;

public enum PrintMessage {
    MAIN_MENU("1. Create an account\n" +
            "2. Log into account\n" +
            "0. Exit"),
    ENTER_CARD("\nEnter your card number:"),
    ENTER_PIN("Enter your PIN:"),
    ERROR_LOGIN("\nWrong card number or PIN!\n"),
    LOGIN("\nYou have successfully logged in!\n"),
    CREATE("\nYour card has been created"),
    USER_MENU("1. Balance\n" +
            "2. Add income\n" +
            "3. Do transfer\n" +
            "4. Close account\n" +
            "5. Log out\n" +
            "0. Exit\n"),
    BALANCE("Balance: "),
    LOGOUT("\nYou have successfully logged out!\n"),
    BYE("Bye!\n"),
    ENTER_INCOME("\nEnter income:"),
    INCOME_ADDED("Income was added!\n"),
    DO_TRANSFER("Transfer\n Enter card number:"),
    TRANS_MISTAKE("Probably you made a mistake in the card number. Please try again!\n"),
    TRANS_NO_CARD("Such a card does not exist."),
    TRANS_HOW_MUCH("Enter how much money you want to transfer:"),
    TRANS_NO_ENOUGH("Not enough money!\n"),
    TRANS_SUCCESS("Success!\n"),
    CLOSE_ACC("The account has been closed!\n");

    String message;

    PrintMessage(String message) {
        this.message = message;
    }

    public void print() {
        System.out.println(message);
    }

}

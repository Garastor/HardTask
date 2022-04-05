package banking.entity;

public enum PrintMessage {
    HELLO("1. Create an account\n" +
            "2. Log into account\n" +
            "0. Exit"),
    ENTERCARD("\nEnter your card number:"),
    ENTERPIN("Enter your PIN:"),
    ERRORLOGIN("\nWrong card number or PIN!\n"),
    LOGIN("\nYou have successfully logged in!\n"),
    CREATE("\nYour card has been created"),
    MENU("1. Balance\n" +
            "2. Add income\n" +
            "3. Do transfer\n" +
            "4. Close account\n" +
            "5. Log out\n" +
            "0. Exit\n"),
    BALANCE("Balance: "),
    LOGOUT("\nYou have successfully logged out!\n"),
    BYE("Bye!\n"),
    ENTERINCOME("\nEnter income:"),
    INCOMEADDED("Income was added!\n"),
    DOTRANSFER("Transfer\n Enter card number:"),
    TRANSMISTAKE("Probably you made a mistake in the card number. Please try again!\n"),
    TRANSNOCARD("Such a card does not exist."),
    TRANSHOWMUCH("Enter how much money you want to transfer:"),
    TRANSNOENOUGH("Not enough money!\n"),
    TRANSSUCCESS("Success!\n"),
    CLOSEACC("The account has been closed!\n");


    String message;

    PrintMessage(String message) {
        this.message = message;
    }

    public void print() {
        System.out.println(message);
    }

}

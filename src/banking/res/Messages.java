package banking.res;

public enum Messages {
    HELLO ("1. Create an account\n" +
            "2. Log into account\n" +
            "0. Exit"),
    ENTERCARD ("\nEnter your card number:"),
    ENTERPIN ("Enter your PIN:"),
    ERRORLOGIN ("\nWrong card number or PIN!\n"),
    LOGIN("\nYou have successfully logged in!\n"),
    CREATE("\nYour card has been created"),
    MENU ("1. Balance\n" +
            "2. Log out\n" +
            "0. Exit"),
    BALANCE ("Balance: "),
    LOGOUT ("\nYou have successfully logged out!\n"),
    BYE ("Bye!\n");

    String message;

    Messages (String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

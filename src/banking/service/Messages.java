package banking.service;

public enum Messages {
    HELLO ("1. Create an account\n" +
            "2. Log into account\n" +
            "0. Exit"),
    ENTERCARD ("Enter your card number:"),
    ENTERPIN ("Enter your PIN:"),
    ERRORLOGIN ("Wrong card number or PIN!\n"),
    LOGIN("You have successfully logged in!\n"),
    CREATE("Your card has been created\n"),
    MENU ("1. Balance\n" +
            "2. Log out\n" +
            "0. Exit"),
    BALANCE ("Balance: "),
    LOGOUT ("You have successfully logged out!\n"),
    BYE ("Bye!\n");

    String message;

    Messages (String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

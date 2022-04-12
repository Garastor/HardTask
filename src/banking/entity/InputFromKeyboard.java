package banking.entity;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum InputFromKeyboard {

    MAIN_MENU("[0-2]"),
    USER_MENU("[0-5]"),
    CARD_NUMBER("[0-9]{16}"),
    CARD_PIN("[0-9]{4}"),
    DIGITS("\\d+");

    private final String regEx;
    private final Scanner scanner = new Scanner(System.in);

    InputFromKeyboard(String regEx) {
        this.regEx = regEx;
    }

    public String get() {
        while (true) {
            String s = scanner.next();
            if (check(s)) {
                return s;
            }
        }
    }

    private boolean check(String input) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}

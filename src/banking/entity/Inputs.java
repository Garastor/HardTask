package banking.entity;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Inputs {

    MENU("[0-2]"),
    MENU2("[0-5]"),
    NUMBER("[0-9]{16}"),
    PIN("[0-9]{4}"),
    DIGITS("\\d+");

    private String regEx;
    private final Scanner scanner = new Scanner(System.in);

    private Inputs(String regEx) {
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

    private String getRegEx() {
        return regEx;
    }

    private boolean check(String input) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}

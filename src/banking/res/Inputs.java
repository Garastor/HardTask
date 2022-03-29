package banking.res;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Inputs {

    MENU ("[0-2]"),
    MENU2 ("[0-5]"),
    NUMBER("[0] | [0-9]{16}"),
    PIN ("[0] | [0-9]{4}");

    String regEx;

    Inputs (String regEx) {
        this.regEx = regEx;
    }

    String getRegEx (){
        return regEx;
    }

    public boolean check (String input) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}

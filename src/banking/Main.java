package banking;

import banking.session.Session;

public class Main {
    public static void main(String[] args) {
        Session session = new Session();
        session.sessionStart();
    }
}
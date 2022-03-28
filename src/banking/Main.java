package banking;

import banking.db.Connect;
import banking.entity.Account;
import banking.session.Session;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Session session = new Session();
        session.sessionStart();

        String filename = null;

//        for (int i = 0; i < args.length; i = i + 2) {
//            if (args[i].equals("-fileName")) {
//                filename = args[i + 1];
//            }
//        }
//
//        if (filename != null) {
//            //        Session session = new Session();
////        session.sessionStart();
//        } else {
//            System.out.println("The argument fileName is required to start the application.");
//        }
//    }

}
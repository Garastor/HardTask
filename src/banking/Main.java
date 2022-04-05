package banking;

import banking.menu.MainMenu;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        MainMenu mainMenu = new MainMenu();
        mainMenu.startMenu();

    }
}



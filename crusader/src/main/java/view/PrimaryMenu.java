package view;

import controller.Application;
import controller.DBController;
import controller.MainController;
import enumeration.commands.Commands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class PrimaryMenu {
    public static void run(Scanner scanner) {
        String input;
        Matcher exitGameMatcher;
        while (true) {
            printSelectMenuMessage();
            input = scanner.nextLine();
            exitGameMatcher = Commands.getMatcher(input, Commands.EXIT_CRUSADER);
            if (input.equals("1") || input.equals("Login Menu")) {
                LoginMenu.run(scanner);
            } else if (input.equals("2") || input.equals("Signup Menu")) {
                SignupMenu.run(scanner);
            } else if (exitGameMatcher.matches()) {
                Application.setCurrentUser(null);
                DBController.saveCurrentUser();
                Application.setStayLoggedIn(false);
                MainController.exitCrusader();
                return;
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    public static void printSelectMenuMessage() {
        System.out.println("Select one of the following menus:");
        System.out.println("1.Login Menu");
        System.out.println("2.Signup Menu");
    }
}

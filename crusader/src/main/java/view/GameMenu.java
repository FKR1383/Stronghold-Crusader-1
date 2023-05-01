package view;

import controller.GameController;
import controller.MainController;
import controller.ViewController;
import enumeration.answers.Answers;
import enumeration.commands.Commands;
import enumeration.commands.GameMenuCommands;
import enumeration.commands.UnitMenuCommands;
import model.game.Game;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {

    public static void run(Scanner scanner, Game game) {
        String input, output;
        System.out.println("<< Game Menu >>");
        //TODO add try catch of parseInt
        while (true) {
            input = scanner.nextLine();
            Matcher dropBuildingMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.DROP_BUILDING);
            Matcher selectBuildingMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SELECT_BUILDING);
            Matcher selectUnitMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SELECT_UNIT);
            Matcher governmentMenuMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.GOVERNMENT_MENU);
            Matcher tradeMenuMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.TRADE_MENU);
            Matcher setUnitStateMatcher = UnitMenuCommands.getMatcher(input, UnitMenuCommands.SET_UNIT_STATE);
            Matcher nextTurnMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.NEXT_TURN);
            Matcher showPlayerMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_PLAYER);
            Matcher showRoundMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_ROUND);
            Matcher backMatcher = Commands.getMatcher(input, Commands.BACK);
            Matcher exitMatcher = Commands.getMatcher(input, Commands.EXIT_CRUSADER);
            // to be continued.?
            if (dropBuildingMatcher.matches()) {
                String items = dropBuildingMatcher.group("items");
                ArrayList<String> itemsPattern = new ArrayList<>();
                itemsPattern.add(GameMenuCommands.X_ITEM.getRegex());
                itemsPattern.add(GameMenuCommands.Y_ITEM.getRegex());
                itemsPattern.add(GameMenuCommands.TYPE_ITEM.getRegex());
                if (ViewController.isItemMatch(items, itemsPattern)) {
                    int x = ViewController.getNumberOfRegex("x");
                    int y = ViewController.getNumberOfRegex("y");
                    String type = ViewController.resultMatcher.group("type");
                    type = ViewController.editItem(type);
                    output = GameController.dropBuilding(x, y, type);
                    System.out.println(output);
                }
            } else if (selectBuildingMatcher.matches()) {
                String items = selectBuildingMatcher.group("items");
                ArrayList<String> itemsPattern = new ArrayList<>();
                itemsPattern.add(GameMenuCommands.X_ITEM.getRegex());
                itemsPattern.add(GameMenuCommands.Y_ITEM.getRegex());
                if (ViewController.isItemMatch(items, itemsPattern)) {
                    int x = ViewController.getNumberOfRegex("x");
                    int y = ViewController.getNumberOfRegex("y");
                    output = GameController.selectBuilding(x, y);
                    System.out.println(output);
                }

            } else if (selectUnitMatcher.matches()) {
                String items = selectUnitMatcher.group("items");
                ArrayList<String> itemsPattern = new ArrayList<>();
                itemsPattern.add(GameMenuCommands.X_ITEM.getRegex());
                itemsPattern.add(GameMenuCommands.Y_ITEM.getRegex());
                if (ViewController.isItemMatch(items, itemsPattern)) {
                    int x = ViewController.getNumberOfRegex("x");
                    int y = ViewController.getNumberOfRegex("y");
                    output = GameController.selectUnit(x, y, scanner);
                    System.out.println(output);
                }
            } else if (setUnitStateMatcher.matches()) {
                String items = setUnitStateMatcher.group("items");
                ArrayList<String> itemsPattern = new ArrayList<>();
                itemsPattern.add(UnitMenuCommands.X_ITEM.getRegex());
                itemsPattern.add(UnitMenuCommands.Y_ITEM.getRegex());
                itemsPattern.add(UnitMenuCommands.S_ITEM.getRegex());
                if (ViewController.isItemMatch(items, itemsPattern)) {
                    int x = ViewController.getNumberOfRegex("x");
                    int y = ViewController.getNumberOfRegex("y");
                    String state = ViewController.resultMatcher.group("state");
                    state = ViewController.editItem(state);
                    output = GameController.setStateOfMilitary(x, y, state);
                    System.out.println(output);
                }
            } else if (governmentMenuMatcher.matches()) {
                GovernmentMenu.run(scanner);
            } else if (tradeMenuMatcher.matches()) {
                TradeMenu.run(scanner);
            } else if (nextTurnMatcher.matches()) {
                output = GameController.changeTurn();
                System.out.println(output);
            } else if (showPlayerMatcher.matches()) {
                output = GameController.changeTurn();
                System.out.println(output);
            } else if (showRoundMatcher.matches()) {
                output = GameController.changeTurn();
                System.out.println(output);
            } else if (backMatcher.matches()) {
                break;
            } else if (exitMatcher.matches()) {
                MainController.exitCrusader();
            } else {
                System.out.println(Answers.INVALID_COMMAND.getValue());
            }
        }
    }
}

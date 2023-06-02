package view.menus;

import controller.DBController;
import controller.GameController;
import controller.MapController;
import controller.gamestructure.GameMaps;
import enumeration.dictionary.Colors;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Government;
import model.building.castlebuildings.MainCastle;
import model.game.Game;
import model.game.Map;
import model.human.military.EuropeanTroop;
import model.menugui.*;
import view.controllers.ViewController;

import java.util.ArrayList;

public class CreateGameMenu extends Application {
    private Stage stage;
    public static Pane createGamePane;
    private Game game;
    private ArrayList<String> castles;
    private ArrayList<Colors> colors;
    public MenuBox menuBox;
    public MenuChoiceBox mapsField;
    public Text governmentTitle;
    public Button addGovernment;
    public ArrayList<MenuTextField> governmentUsernames = new ArrayList<>();
    public ArrayList<MenuChoiceBox> governmentColors = new ArrayList<>();
    public ArrayList<MenuChoiceBox> castleNumbers = new ArrayList<>();
    public MenuButton startGame;
    public int governmentNumber;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        governmentNumber = 0;
        colors = new ArrayList<>();
        for (Colors color : Colors.values()) {
            colors.add(color);
        }

        DBController.loadAllUsers();
        Pane root = FXMLLoader.load(getClass().getResource("/FXML/signupMenu.fxml"));
        createGamePane = ViewController.makePaneScreen(stage, root, 1000, -1);
        Scene scene = new Scene(root);

        menuBox = new MenuBox("Signup", 365, 100, 800, 700);
        root.getChildren().add(menuBox);

        makeTitleStuff();
        makeGovernmentStuff();
        checkSelectedMap();
        addGovernment.setOnAction(actionEvent -> addGovernment());
        makeBackButton();
        makeStartGameButton();


        menuBox.getChildren().add(addGovernment);
        stage.setTitle("Create New Game");
        stage.setScene(scene);
        stage.show();
    }

    private void makeTitleStuff() {
        String[] maps = {"Map 1", "Map 2"};
        mapsField = new MenuChoiceBox(menuBox, "Map", -90, -250,
                FXCollections.observableArrayList(maps), 300);
        menuBox.getChildren().add(mapsField);

        governmentTitle = new Text("Governments");
        governmentTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        governmentTitle.setTranslateX(-305);
        governmentTitle.setTranslateY(-190);
        menuBox.getChildren().add(governmentTitle);

        addGovernment = new Button("+");
        addGovernment.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        addGovernment.setTranslateX(40);
        addGovernment.setTranslateY(-190);
        addGovernment.setDisable(true);
        addGovernment.getStyleClass().add("addButton");
    }

    private void makeGovernmentStuff() {
        for (int i = 1; i <= 8; i++) {
            MenuTextField governmentUsername = new MenuTextField(menuBox, "username",
                    "Government " + i, -140, 60 * i - 190, 200);
            governmentUsername.setDisable(true);
            menuBox.getChildren().add(governmentUsername);
            governmentUsernames.add(governmentUsername);

            MenuChoiceBox colorField = new MenuChoiceBox(menuBox, "", -10, 60 * i - 190,
                    FXCollections.observableArrayList(colors), 40);
            colorField.setDisable(true);
            menuBox.getChildren().add(colorField);
            governmentColors.add(colorField);
            checkSelectedColor(colorField);

            MenuChoiceBox castleNumber = new MenuChoiceBox(menuBox, "", 40, 60 * i - 190,
                    FXCollections.observableArrayList(new String[1]), 40);
            castleNumber.setDisable(true);
            menuBox.getChildren().add(castleNumber);
            castleNumbers.add(castleNumber);
        }
    }

    private void makeBackButton() {
        MenuFingerBack back = new MenuFingerBack(-450, 350);
        back.setScaleX(0.7);
        back.setScaleY(0.7);
        back.setOnAction(actionEvent -> {
            MapController.map = null;
            game = null;
            GameController.setGame(null);
            MainMenu mainMenu = new MainMenu();
            try {
                mainMenu.start(this.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        menuBox.getChildren().add(back);
    }

    private void makeStartGameButton() {
        startGame = new MenuButton("Start Game", menuBox, 240, 290, true);
        startGame.setOnAction(actionEvent -> {
            if (governmentNumber < 3) return;
            GameMenu gameMenu = new GameMenu();
            try {
                gameMenu.start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        menuBox.getChildren().add(startGame);
    }

    private void checkSelectedMap() {
        mapsField.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if (newValue.equals("")) {
                    governmentUsernames.get(0).setDisable(true);
                    castleNumbers.get(0).setDisable(true);
                    governmentColors.get(0).setDisable(true);
                } else {
                    governmentNumber = 1;
                    governmentUsernames.get(0).setDisable(false);
                    castleNumbers.get(0).setDisable(false);
                    governmentColors.get(0).setDisable(false);
                    governmentUsernames.get(0).setText(controller.Application.getCurrentUser().getUsername());
                    governmentUsernames.get(0).setEditable(false);
                    addGovernment.setDisable(false);
                    castles = new ArrayList<>();
                    GameMaps.createMaps();
                    Map selectedMap = (mapsField.getValue().equals("Map 1")) ?
                            GameMaps.largeMaps.get(0) : GameMaps.smallMaps.get(0);
                    for (int i = 0; i < 8; i++)
                        castles.add("Castle " + (i + 1) + " (" + selectedMap.getDefaultCastles().get(i).getFirst()
                                + ", " + selectedMap.getDefaultCastles().get(i).getSecond() + ")");
                    castleNumbers.get(0).setItems(FXCollections.observableArrayList(castles));
                    MapController.map = selectedMap;
                    game = new Game(selectedMap);
                    GameController.setGame(game);
                }
            }
        });
    }

    private void checkSelectedColor(MenuChoiceBox colors) {
        colors.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                Colors color = (Colors) newValue;
                colors.setStyle("-fx-background-color: " + color.getRgb());
            }
        });
    }

    private void addGovernment() {
        governmentUsernames.get(governmentNumber - 1).clearErrorOrMessage();
        if (governmentUsernames.get(governmentNumber - 1).getText().equals("")) {
            governmentUsernames.get(governmentNumber - 1).handlingError("username is required!");
            return;
        }
        if (!controller.Application.isUserExistsByName(governmentUsernames.get(governmentNumber - 1).getText())) {
            governmentUsernames.get(governmentNumber - 1).handlingError("username doesn't exist!");
            return;
        }
        if (governmentColors.get(governmentNumber - 1).getValue() == null) {
            governmentUsernames.get(governmentNumber - 1).handlingError("color is required!");
            return;
        }
        if (castleNumbers.get(governmentNumber - 1).getValue() == null) {
            governmentUsernames.get(governmentNumber - 1).handlingError("castle number is required!");
            return;
        }

        int castleNumber = Integer.parseInt(((String) castleNumbers.get(governmentNumber - 1).getValue()).substring(7, 8));
        int x = game.getMap().getDefaultCastles().get(castleNumber - 1).getFirst();
        int y = game.getMap().getDefaultCastles().get(castleNumber - 1).getSecond();
        castles.remove("Castle " + castleNumber + " (" + game.getMap().getDefaultCastles().get(castleNumber - 1).getFirst()
                + ", " + game.getMap().getDefaultCastles().get(castleNumber - 1).getSecond() + ")");

        Colors color = (Colors) governmentColors.get(governmentNumber - 1).getValue();
        colors.remove(color);
        Government government = new Government(controller.Application.getUserByUsername
                (governmentUsernames.get(governmentNumber - 1).getText()), x, y, color);
        MapController.dropMilitary(x, y, "lord", government);
        EuropeanTroop lordMilitary = (EuropeanTroop) game.getMap().getTile(x, y).getMilitaries().get(0);
        lordMilitary.setGovernment(government);
        government.setLord(lordMilitary);
        government.addAmountToProperties("wood", "resource", 100);
        government.addAmountToProperties("stone", "resource", 50);
        government.addAmountToProperties("bread", "food", 60);
        government.setGold(4000);
        game.addGovernment(government);
        MapController.dropBuilding(x, y, "mainCastle", government);
        MainCastle mainCastle = (MainCastle) GameController.getGame().getMap().getTile(x, y).getBuilding();
        mainCastle.setGovernment(government);
        government.setMainCastle(mainCastle);
        mainCastle.makeUnemployed(10);

        if (governmentNumber == 1) mapsField.setDisable(true);
        governmentUsernames.get(governmentNumber - 1).setEditable(false);
        governmentColors.get(governmentNumber - 1).setDisable(true);
        castleNumbers.get(governmentNumber - 1).setDisable(true);
        if (governmentNumber == 7) addGovernment.setDisable(true);
        else {
            governmentNumber++;
            governmentUsernames.get(governmentNumber - 1).setDisable(false);
            governmentColors.get(governmentNumber - 1).setDisable(false);
            castleNumbers.get(governmentNumber - 1).setDisable(false);
            governmentColors.get(governmentNumber - 1).setItems(FXCollections.observableArrayList(colors));
            castleNumbers.get(governmentNumber - 1).setItems(FXCollections.observableArrayList(castles));
        }
    }
}

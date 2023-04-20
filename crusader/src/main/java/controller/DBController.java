package controller;

import controller.gamestructure.GameGoods;
import controller.gamestructure.GameHumans;
import enumeration.Paths;
import model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.goods.Goods;
import model.human.military.ArabianMercenary;
import model.human.military.EuropeanTroop;
import model.human.military.Military;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DBController {


    // load & save users from files
    public static void loadAllUsers(){
        try {
            Gson gson = new Gson();
            checkFileExist(Paths.USERS_PATH.getPath());
            String text = new String(Files.readAllBytes(Path.of(Paths.USERS_PATH.getPath())));
            ArrayList<User> allUsers = gson.fromJson(text, new TypeToken<List<User>>(){}.getType());
            Application.setUsers(allUsers);
        } catch (IOException e) {
            System.out.println("An error occurred.[load users]");
            e.printStackTrace();
        }
    }

    public static void saveAllUsers(){

        try {
            checkFileExist(Paths.USERS_PATH.getPath());
            File file = new File(Paths.USERS_PATH.getPath());
            FileWriter fileWriter = new FileWriter(file);
            if(Application.getUsers() != null){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(Application.getUsers());
                fileWriter.write(json);
            }else {
                fileWriter.write("");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.[save users]");
            e.printStackTrace();
        }
    }

    public static void loadCurrentUser(){
        try {
            Gson gson = new Gson();
            checkFileExist(Paths.CURRENT_USER_PATH.getPath());
            String text = new String(Files.readAllBytes(Path.of(Paths.CURRENT_USER_PATH.getPath())));
            User user = gson.fromJson(text, User.class);
            if (user != null) {
                User currentUser = Application.getUserByUsername(user.getUsername());
                Application.setCurrentUser(currentUser);
            } else {
                Application.setCurrentUser(null);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.[load current user]");
            e.printStackTrace();
        }
    }

    public static void saveCurrentUser(){
        try {
            checkFileExist(Paths.CURRENT_USER_PATH.getPath());
            File file = new File(Paths.CURRENT_USER_PATH.getPath());
            FileWriter fileWriter = new FileWriter(file);
            if(Application.getCurrentUser() != null){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(Application.getCurrentUser());
                fileWriter.write(json);
            }else {
                fileWriter.write("");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.[save current user]");
            e.printStackTrace();
        }
    }


    // load goods from file
    public static void loadGoods(){
        try {
            Gson gson = new Gson();
            checkFileExist(Paths.GOODS_PATH.getPath());
            String text = new String(Files.readAllBytes(Path.of(Paths.GOODS_PATH.getPath())));
            GameGoods.goods = gson.fromJson(text, new TypeToken<HashMap<String, Goods>>(){}.getType());
            loadFoods();
            loadResources();
            loadWeapons();
        } catch (IOException e) {
            System.out.println("An error occurred.[load goods]");
            e.printStackTrace();
        }
    }

    private static void loadFoods(){
        try {
            Gson gson = new Gson();
            checkFileExist(Paths.FOODS_PATH.getPath());
            String text = new String(Files.readAllBytes(Path.of(Paths.FOODS_PATH.getPath())));
            GameGoods.foods = gson.fromJson(text, new TypeToken<HashMap<String, Goods>>(){}.getType());
        } catch (IOException e) {
            System.out.println("An error occurred.[load goods]");
            e.printStackTrace();
        }
    }
    private static void loadResources(){
        try {
            Gson gson = new Gson();
            checkFileExist(Paths.RESOURCES_PATH.getPath());
            String text = new String(Files.readAllBytes(Path.of(Paths.RESOURCES_PATH.getPath())));
            GameGoods.resources = gson.fromJson(text, new TypeToken<HashMap<String, Goods>>(){}.getType());
        } catch (IOException e) {
            System.out.println("An error occurred.[load goods]");
            e.printStackTrace();
        }
    }
    private static void loadWeapons(){
        try {
            Gson gson = new Gson();
            checkFileExist(Paths.WEAPONS_PATH.getPath());
            String text = new String(Files.readAllBytes(Path.of(Paths.WEAPONS_PATH.getPath())));
            GameGoods.weapons = gson.fromJson(text, new TypeToken<HashMap<String, Goods>>(){}.getType());
        } catch (IOException e) {
            System.out.println("An error occurred.[load goods]");
            e.printStackTrace();
        }
    }


    //load military from file
    public static void loadMilitary(){
        HashMap<String, EuropeanTroop> europeanTroops = loadEuropeanTroops();
        HashMap<String, ArabianMercenary> arabianMercenaries = loadArabianMercenaries();

        GameHumans.militaries.putAll(europeanTroops);
        GameHumans.militaries.putAll(arabianMercenaries);

    }

    public static HashMap<String, EuropeanTroop> loadEuropeanTroops(){
        try {
            Gson gson = new Gson();
            checkFileExist(Paths.EUROPEAN_TROOP_PATH.getPath());
            String text = new String(Files.readAllBytes(Path.of(Paths.EUROPEAN_TROOP_PATH.getPath())));
            HashMap<String, EuropeanTroop> europeanTroopHashMap= gson.fromJson(text, new TypeToken<HashMap<String, EuropeanTroop>>(){}.getType());
            return europeanTroopHashMap;
        } catch (IOException e) {
            System.out.println("An error occurred.[load humans]");
            e.printStackTrace();
        }
        return null;
    }

    public static HashMap<String, ArabianMercenary> loadArabianMercenaries(){
        try {
            Gson gson = new Gson();
            checkFileExist(Paths.ARABIAN_MERCENARY_PATH.getPath());
            String text = new String(Files.readAllBytes(Path.of(Paths.ARABIAN_MERCENARY_PATH.getPath())));
            HashMap<String, ArabianMercenary> arabianMercenaryHashMap= gson.fromJson(text, new TypeToken<HashMap<String, ArabianMercenary>>(){}.getType());
            return arabianMercenaryHashMap;
        } catch (IOException e) {
            System.out.println("An error occurred.[load humans]");
            e.printStackTrace();
        }
        return null;
    }



    public static void checkFileExist(String fileAddress){
        try {
            File myObj = new File(fileAddress);
            boolean check = myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.[check file exist]");
            e.printStackTrace();
        }
    }
}

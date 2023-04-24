package model;

import java.lang.String;
import enumeration.dictionary.Foodstuffs;
import enumeration.dictionary.RawMaterials;
import model.building.Building;
import model.building.castlebuildings.MainCastle;
import model.goods.Goods;
import model.human.Human;
import model.human.military.ArabianMercenary;
import model.human.military.EuropeanTroop;
import model.buildinghandler.BuildingCounter;
import model.buildinghandler.Storage;
import model.human.military.Lord;
import model.human.military.Military;

import java.util.ArrayList;
import java.util.HashMap;

public class Government {
    private User user;

    private HashMap<String,Trade> trades = new HashMap<>();

    private HashMap<String,Trade> newTrades = new HashMap<>();

    public HashMap<String, Integer> getProperties() {
        return properties;
    }

    public HashMap<String, Storage> getStorages() {
        return storages;
    }

    private HashMap<String, Integer> properties = new HashMap<>();
    private HashMap<String, Storage> storages = new HashMap<>();
    private HashMap<String, BuildingCounter> buildings = new HashMap<>();

    private ArrayList<Military> troops = new ArrayList<>();
    private ArrayList<Human> society = new ArrayList<>();

    private int foodRate;

    public HashMap<String, BuildingCounter> getBuildings() {
        return buildings;
    }

    private int fearRate;
    private int religionRate;
    private int taxRate;
    private int gold;
    private int population, maxPopulation;
    private int castleX, castleY;
    private String color;
    private Lord lord;

    public Lord getLord() {
        return lord;
    }

    public void setLord(Lord lord) {
        this.lord = lord;
        ((MainCastle)(this.getBuildings().get("MainCastle").getBuildings().get(0))).setLord(lord);
    }

    public Government(User user, int castleX, int castleY, String color) {
        this.user = user;
        this.castleX = castleX;
        this.castleY = castleY;
        this.color = color;
    }

    public void addAmountToProperties(String itemName, String itemType, int amount) {
        this.getProperties().put(itemName, this.getProperties().get(itemName) + amount);
        if(itemType != null){
            this.getStorages().get(itemType).addAmount(amount);
        }
    }


    public ArrayList<Human> getSociety() {
        return society;
    }

    public void setSociety(ArrayList<Human> society) {
        this.society = society;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public int getReligionRate() {
        return religionRate;
    }

    public void setReligionRate(int religionRate) {
        this.religionRate = religionRate;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
        ((MainCastle)(this.getBuildings().get("MainCastle").getBuildings().get(0))).setTaxRate(taxRate);
    }
    public void addGold(int amount) {
        this.gold += amount;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public void setMaxPopulation(int maxPopulation) {
        this.maxPopulation = maxPopulation;
    }

    public int getCastleX() {
        return castleX;
    }

    public void setCastleX(int castleX) {
        this.castleX = castleX;
    }

    public int getCastleY() {
        return castleY;
    }

    public void setCastleY(int castleY) {
        this.castleY = castleY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void changePopulation() {
    }

    public int getPopularity() {
        return 0;
    }

    public int getPropertyAmount(String name){
        return properties.get(name);
    }

    public User getUser() {
        return user;
    }

    public HashMap<String, Trade> getTrades() {
        return trades;
    }

    public HashMap<String, Trade> getNewTrades() {
        return newTrades;
    }

    public ArrayList<Military> getTroops() {
        return troops;
    }

    public void addTrade(Trade trade){
        trades.put(trade.getId(), trade);
        newTrades.put(trade.getId(), trade);
    }

    public void clearTradeCash(){
        newTrades.clear();
    }
}

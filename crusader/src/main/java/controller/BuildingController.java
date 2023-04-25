package controller;

import enumeration.answers.BuildingAnswers;
import model.Government;
import model.building.Building;
import model.building.castlebuildings.Gatehouse;
import model.building.producerbuildings.Barrack;
import model.building.producerbuildings.WeaponProducer;
import model.building.storagebuildings.StorageBuilding;
import model.human.military.Military;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayList;
import java.util.HashMap;

public class BuildingController {
    private static Building building;
    private static Government government;

    private static Government getGovernment() {
        return government;
    }

    public static void setGovernment() {
        if (building != null)
            BuildingController.government = BuildingController.getBuilding().getGovernment();
    }

    public static Building getBuilding() {
        return building;
    }

    private static void setBuilding(Building b) {
        building = b;
    }

    public static String changeTaxRate(String rateNumberString){
        int rateNumber;
        try {
            rateNumber = Integer.parseInt(rateNumberString);
        } catch (Exception e) {
            return BuildingAnswers.getMessage(BuildingAnswers.INVALID_NUMBER_INPUT);
        }
        if (rateNumber < -3 || rateNumber > 8) {
            return BuildingAnswers.getMessage(BuildingAnswers.INVALID_RANGE_FOR_TAX_RATE);
        }
        government.setTaxRate(rateNumber);
        return BuildingAnswers.getMessage(BuildingAnswers.TAX_RATE_SUCCESSFULLY_CHANGED);
    }

    public static String showTaxRate() {
        return BuildingAnswers.getMessage(BuildingAnswers.TAX_RATE_SHOWING) + government.getTaxRate();
    }

    public static String openOrCloseGatehouse(String order) {
        boolean openIt = order.equals("open");
        ((Gatehouse)building).openOrCloseGatehouse(openIt);
        return BuildingAnswers.getMessage(BuildingAnswers.OPEN_CLOSE_SUCCESSFULLY_DONE);
    }

    public static String resourcesNeededForRepair() {
        double rateOfRepairNeeded = ((double)building.getHp())/building.getMaxHp();
        String result = "Items in need for repair : \n";
        for (String item : building.getCost().keySet()) {
            result += item + " : " + (int)(rateOfRepairNeeded*building.getCost().get(item)) + "\n";
        }
        return result;
    }

    public static String repair() {
        String itemNeeded = "";
        boolean canRepaired = true;
        double rateOfRepairNeeded = ((double)building.getHp())/building.getMaxHp();
        for (String item : building.getCost().keySet()) {
            if (government.getPropertyAmount(item) < (int)(rateOfRepairNeeded*building.getCost().get(item))) {
                itemNeeded = item;
                canRepaired = false;
                break;
            }
        }
        if (!canRepaired) {
            return "You don't have enough " + itemNeeded + " for repair!";
        }
        for (String item : building.getCost().keySet()) {
            GovernmentController.consumeProduct(government, item , (int)(rateOfRepairNeeded*building.getCost().get(item)));
        }
        building.setHp(building.getMaxHp());
        return "Successfully repaired!";
    }

    public static String showStateOfGate() {
        return ((Gatehouse)building).isOpen() ? "Open" : "Close";
    }

    public static String showItems() {
        String result = "";
        HashMap<String , Integer> items = ((StorageBuilding)building).getItems();
        for (String name : items.keySet()) {
            result += name + " : " + items.get(name) + "\n";
        }
        return result;
    }

    public static String showUnitsList() {
        Barrack barrack = (Barrack) building;
        String result = "";
        int counter = 0;
        for (String name : barrack.getUnits()) {
            counter++;
            result += "unit " + counter + ": " + name + "\n";
        }
        return result;
    }

    public static String buyUnit(String unitName) {
        Barrack barrack = (Barrack) building;
        if (!barrack.getUnits().contains(unitName)) {
            return BuildingAnswers.getMessage(BuildingAnswers.INVALID_UNIT_NAME);
        }
        if(!barrack.checkGold(unitName)){
            return BuildingAnswers.getMessage(BuildingAnswers.INSUFFICIENT_MONEY);
        }

        if(!barrack.checkRequired(unitName)){
            return BuildingAnswers.getMessage(BuildingAnswers.INSUFFICIENT_RESOURCE);
        }

        barrack.makeUnit(unitName);
        return unitName +" added successfully!";
    }


    public static void changeWeapon(String name){
        if(building instanceof WeaponProducer weaponProducer){
            weaponProducer.changeItemName(name);
        }
    }
    public static String showSavedGoods() {
        StringBuilder result = new StringBuilder();
        StorageBuilding stockpile = (StorageBuilding) building;
        for (String itemName : stockpile.getItems().keySet()) {
            result.append(itemName).append(" : ").append(stockpile.getItemAmount(itemName)).append("\n");
        }
        return result.toString();
    }

    public static String howManyHorses() {
        int availableHorses = government.getPropertyAmount("horse");
        int horsesInUse = 0;
        for (Military military : government.getTroops()) {
            if (military.getName().equals("knight")) {
                horsesInUse++;
            }
        }
        return "Available horses: " + availableHorses + "\nUsed horses: " + horsesInUse;
    }

}

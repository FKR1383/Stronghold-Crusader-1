package controller.gamestructure;

import model.goods.Goods;

import java.util.HashMap;

public class GameGoods {
    public static HashMap<String, Goods> goods = new HashMap<>();
    public static HashMap<String, Goods> foods = new HashMap<>();
    public static HashMap<String, Goods> weapons = new HashMap<>();
    public static HashMap<String, Goods> resources = new HashMap<>();

    public static Goods getProduct(String name) {
        return goods.get(name);
    }

    public static void addGoods() {
        GameFoods.addFoods();
        GameResources.addResources();
        GameWeapons.addWeapons();
    }
}

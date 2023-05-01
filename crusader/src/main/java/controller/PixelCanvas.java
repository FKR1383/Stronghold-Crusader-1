package controller;

import controller.gamestructure.GameMaps;
import model.building.castlebuildings.MainCastle;
import model.game.Map;
import model.game.Tile;

import javax.swing.*;
import java.awt.*;

public class PixelCanvas extends Canvas {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static Map map = null;

    private static int  zoomRate;
    public void paint(Graphics g) {
        super.paint(g);
        for(int i = 0; i < WIDTH; i++) {
            for(int j = 0; j < HEIGHT; j++) {
                int x = i/zoomRate;
                int y = j/zoomRate;
                g.setColor(getColor(x,y,map));
                g.drawLine(i, j, i, j);
            }
        }
    }

    private Color getColor(int x, int y,Map map) {
        Tile tile = map.getTile(x,y);
        if(tile.getMilitaries().size() != 0) {
            String color = tile.getMilitaries().get(0).getGovernment().getColor();
            return getGovernmentColor(color);
        }else if(tile.getBuilding() != null){
            if(tile.getBuilding() instanceof MainCastle){
                return new Color(0,0,0);
            }
            return new Color(83, 62, 62);
        }else{
            return new Color(tile.getTexture().getR(), tile.getTexture().getB(), tile.getTexture().getG());
        }
        //return new Color(143, 14, 2);
    }

    public Color getGovernmentColor(String color){
        switch (color){
            case "blue":
                return new Color(27, 51, 204);
            case "red" :
                return new Color(204, 27, 48);
            case "orange":
                return new Color(234, 137, 27);
            default:
                return new Color(140, 140, 140);
        }
    }
    public static void drawMap(Map mapTest) {
        map = mapTest;
        zoomRate = 800/map.getLength();
        JFrame frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.add(new PixelCanvas());
        frame.setVisible(true);
    }
}
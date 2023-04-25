package model.game;

import enumeration.Textures;
import enumeration.dictionary.Trees;
import model.building.Building;
import model.human.civilian.Civilian;
import model.human.civilian.Civilian;
import model.human.military.Military;

import java.util.ArrayList;

public class Tile {
    public Tile() {
        this.isMoat = false;
        this.isPit = false;
    }

    private Textures texture = Textures.EARTH;
    private Trees tree;

    public Trees getTree() {
        return tree;
    }

    public void setTree(Trees tree) {
        this.tree = tree;
    }

    public boolean isCanPutBuilding() {
        return canPutBuilding;
    }

    private Building building = null;
    private ArrayList<Civilian> civilians = new ArrayList<>();

    public ArrayList<Military> getMilitaries() {
        return militaries;
    }

    public void setMilitaries(ArrayList<Military> militaries) {
        this.militaries = militaries;
    }

    private ArrayList<Military> militaries = new ArrayList<>();
    private boolean isMoat;
    private boolean isPit;

    private boolean passable = true;
    private boolean canPutBuilding = true;

    public ArrayList<Civilian> getCivilians() {
        return civilians;
    }

    public void setCivilians(ArrayList<Civilian> civilian) {
        this.civilians = civilian;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public boolean getCanPutBuilding() {
        return canPutBuilding;
    }

    public void setCanPutBuilding(boolean canPutBuilding) {
        this.canPutBuilding = canPutBuilding;
    }

    public Tile(Textures texture) {
        this.texture = texture;
    }

    public Textures getTexture() {
        return texture;
    }

    public void setTexture(Textures texture) {
        if(building == null){
            this.texture = texture;
            passable = texture.isPassable();
            if(!texture.isPassable()){
                civilians.clear();
            }
            canPutBuilding = texture.getCanPutBuilding();
        }
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public ArrayList<Civilian> getCivilian() {
        return civilians;
    }

    public void setCivilian(ArrayList<Civilian> human) {
        this.civilians = human;
    }

    public void addMilitary(Military military){
        militaries.add(military);
    }

    public boolean isMoat() {
        return isMoat;
    }

    public void setMoat(boolean moat) {
        isMoat = moat;
    }

    public boolean isPit() {
        return isPit;
    }

    public void setPit(boolean pit) {
        isPit = pit;
    }

    public void clearCivilian(){
        civilians.clear();
    }

    public void clearMilitary(){
        militaries.clear();
    }
}

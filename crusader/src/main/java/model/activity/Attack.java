package model.activity;

import controller.GameController;
import controller.MapController;
import controller.human.HumanController;
import controller.human.MoveController;
import enumeration.MilitaryStates;
import enumeration.MoveStates;
import model.building.Building;
import model.game.Map;
import model.game.Tuple;
import model.human.civilian.Civilian;
import model.human.military.Engineer;
import model.human.military.Military;
import model.tools.Tool;

import java.util.ArrayList;
import java.util.Random;

public class Attack {
    Building targetBuilding;
    Military military;
    Military enemy;
    Tool tool;

    public Attack(Military military) {
        this.military = military;
    }

    public ArrayList<Military> getEnemyOfRange(int x, int y, int range) {
        int startX = x - range;
        int startY = y - range;
        int endX = x + range;
        int endY = y + range;
        Map map = GameController.getGame().getMap();
        if (x - range < 0) {
            startX = 0;
        }

        if (y - range < 0) {
            startY = 0;
        }

        if (endX + 1 >= map.getWidth()) {
            endX = map.getWidth() - 1;
        }

        if (endY + 1 >= map.getLength()) {
            endY = map.getLength() - 1;
        }
        return HumanController.getEnemiesOfArea(startX, startY, endX, endY,military.getGovernment());
    }


    public void attackCiviliansOfRange(int x, int y, int range) {
        Map map = GameController.getGame().getMap();
        int startX = x - range;
        int endX = x + range;
        int startY = y - range;
        int endY = y + range;


        if (x < range) {
            startX = 0;
        }
        if (endX >= map.getWidth() - 1) {
            endX = map.getWidth() - 1;
        }

        if (endY + 1 >= map.getLength()) {
            endY = map.getLength() - 1;
        }

        if (y - range < 0) {
            startY = 0;
        }

        attackCiviliansOfArea(startX, startY, endX, endY);
    }

    public void attackCiviliansOfArea(int startX, int startY, int endX, int endY) {
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                ArrayList<Civilian> civilians = MapController.getCiviliansOfOtherGovernment(i, j, military.getGovernment());
                if (civilians.size() != 0) {
                    Civilian civilian = civilians.get(0);
                    MapController.deleteHuman(civilian.getX(),civilian.getY(),civilian);
                    civilian.setGovernment(null);
                    return;
                }
            }
        }
    }

    public Tool getToolOfRange(int x, int y, int range) {
        Map map = GameController.getGame().getMap();
        int endX = x + range;
        int endY = y + range;
        int startX = x - range;
        int startY = y - range;

        if (endX + 1 >= map.getWidth()) {
            endX = map.getWidth() - 1;
        }

        if (endY + 1 >= map.getLength()) {
            endY = map.getLength() - 1;
        }

        if (x - range < 0) {
            startX = 0;
        }

        if (y - range < 0) {
            startY = 0;
        }

        return getToolOfArea(startX, startY, endX, endY);
    }

    public Tool getToolOfArea(int startX, int startY, int endX, int endY) {
        double minDistance = 500;
        Tool result = null;
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                Tool tool = GameController.getGame().getMap().getTile(i, j).getTool();
                if (tool == null || tool.getGovernment().equals(military.getGovernment())) {
                    continue;
                }
                Tool targetTool = military.getMove().getTool();
                double distance = MoveController.getDistance(tool.getX(), tool.getY(), military.getX(), military.getY());
                if (tool.equals(targetTool)) {
                    return tool;
                } else if (distance < minDistance) {
                    minDistance = distance;
                    result = tool;
                }
            }
        }
        return result;
    }

    public void setEnemy(Military enemy) {
        targetBuilding = null;
        this.enemy = enemy;
    }

    public void setTargetBuilding(Building targetBuilding) {
        this.targetBuilding = targetBuilding;
    }


    //methods
    public boolean isInRange(int x, int y, int range) {
        if (Math.abs(military.getX() - x) > range) {
            return false;
        }

        return Math.abs(military.getY() - y) <= range;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public boolean shouldAttack(int range) {
        if (enemy != null || tool != null) {
            return true;
        }
        Tool nearestTool = getToolOfRange(military.getX(), military.getY(), range);
        ArrayList<Military> militaries = getEnemyOfRange(military.getX(), military.getY(), range);
        if (militaries.size() == 0 && nearestTool == null) {
            return false;
        }

        double minDistance = 500;
        ArrayList<Military> enemies = new ArrayList<>();

        if (militaries.size() != 0) {
            if (military.getMove() != null && military.getMove().getEnemy() != null && militaries.contains(military.getMove().getEnemy())) {
                Military targetTroop = military.getMove().getEnemy();
                this.enemy = militaries.get(militaries.indexOf(targetTroop));
                return true;
            }
            for (Military enemy : militaries) {
                double distance = MoveController.getDistance(enemy.getX(), enemy.getY(), military.getX(), military.getY());
                if (distance < minDistance && Math.abs(minDistance - distance) > 0.5) {
                    minDistance = distance;
                    enemies.clear();
                    enemies.add(enemy);
                }
            }
        }


        if (nearestTool != null) {
            if (minDistance > MoveController.getDistance(nearestTool.getX(), nearestTool.getY(), military.getX(), military.getY())) {
                this.tool = nearestTool;
                return true;
            }
        }

        Random random = new Random();
        int index = random.nextInt(enemies.size());

        this.enemy = enemies.get(index);
        return true;
    }


    public void attackEnemy() {

        if (tool != null) {
            attackToTool();
            return;
        }

        if (enemy == null) {
            return;
        }

        int enemyHp = enemy.takeDamage(military.getAttackRating());
        if (enemy.getAttack().enemy == null && enemy.getAttack().isInRange(military.getX(), military.getY(), enemy.getShootingRange())) {
            enemy.getAttack().setEnemy(military);
        }

        if (enemyHp <= 0) {
            MapController.deleteMilitary(enemy.getX(), enemy.getY(), enemy);
            enemy.setGovernment(null);
            enemy = null;
        }

    }

    //set attack according type of enemy(he can air attack or not)
    public void attack() {
        if (tool != null) {
            if (military.canAirAttack()) {
                attackToTool();
            } else {
                HumanController.attack(tool);
                tool = null;
                enemy = null;
            }
            return;
        }
        if (enemy != null) {
            if (military.canAirAttack()) {
                attackEnemy();
            } else {
                HumanController.attack(enemy);
                enemy = null;
                tool = null;
            }
        }
    }

    //it determines range of attack according military state
    public boolean shouldAttack() {
        if (military.getMilitaryState().equals(MilitaryStates.AGGRESSIVE_STANCE.getState())) {
            return shouldAttack(military.getAggressiveRange());
        }

        if (military.getMilitaryState().equals(MilitaryStates.DEFENSIVE_STANCE.getState())) {
            return shouldAttack(military.getDefenseRange());
        }
        return shouldAttack(military.getShootingRange());
    }

    public boolean buildingIsInRange(Building building) {
        if (building == null) {
            return false;
        }
        if (military.canAirAttack()) {
            return isInRange(building.getStartX(), building.getStartY(), military.getShootingRange());
        }
        Tuple tuple = new Tuple(military.getY(), military.getX());
        return building.getNeighborTiles().contains(tuple);
    }

    public void attackToBuilding() {
        if (military.getName().equals("ladderman")) {
            military.setUsesLadder(true);
            return;
        }
        int hp = targetBuilding.takeDamage(military.getAttackRating());
        if (hp < 0) {
            MapController.deleteBuilding(targetBuilding);
            targetBuilding.setGovernment(null);
            targetBuilding = null;
        }
    }

    public void attackToTool() {
        int hp = tool.takeDamage(military.getAttackRating());
        if (hp < 0) {
            MapController.deleteTool(tool.getX(), tool.getY(), tool);
            tool.setGovernment(null);
            tool = null;
        }
    }


    //this should use in nextTurn for each troop if troop has government so far
    public void doAttack() {

        if (military instanceof Engineer engineer) {
            if(engineer.isHasOil()){
                HumanController.pourOilDirection(engineer,null, engineer.getMilitaryState());
            }
            return;
        }

        if (enemy != null && enemy.getGovernment() == null){
            enemy = null;
        }

        if(tool != null && tool.getGovernment() == null){
            tool = null;
        }

        if (targetBuilding != null && targetBuilding.getGovernment() == null){
            targetBuilding = null;
        }

        //if enemy is very near to military
        Move move = military.getMove();
        if (shouldAttack(2)) {
            if (move != null && move.isMoving()) {
                move.setAttacking(true);
            }
            if (military.getName().equals("assassin")){
                military.setInvisible(false);
            }
            attackEnemy();
            return;
        }


        //building attack
        if (targetBuilding != null && buildingIsInRange(targetBuilding)) {
            if (move != null && move.isMoving()) {
                move.stopMove();
            }
            attackToBuilding();
            return;
        }


        if (tool != null && isInRange(tool.getX(), tool.getY(), military.getShootingRange())) {
            if (move != null && move.isMoving()) {
                move.setAttacking(true);
            }
            attackToTool();
            return;
        }

        tool = null;
        //continue moving if you don't need to defend yourself
        if (move != null && !move.getMoveState().equals(MoveStates.STOP.getState())) {
            if (move.isAttacking()) {
                move.setAttacking(false);
            }
            return;
        }

        //attack
        if (shouldAttack()) {
            if (military.getName().equals("assassin")){
                military.setInvisible(false);
            }
            attack();
        }else {
            attackCiviliansOfRange(military.getX(), military.getY(), 2);
        }
    }

}

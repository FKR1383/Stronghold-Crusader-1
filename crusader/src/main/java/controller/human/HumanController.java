package controller.human;


import enumeration.MoveStates;
import javafx.util.Pair;
import model.activity.Move;
import model.human.military.Military;

import java.util.ArrayList;
import java.util.LinkedList;


public class HumanController {
    public static ArrayList<Military> militaries;

    public static boolean move(Pair<Integer, Integer> endPair) {

        Pair<Integer, Integer> startPair = MoveController.getStartPair();
        LinkedList<Pair<Integer, Integer>> path = MoveController.checkHasPath(startPair, endPair);
        LinkedList<Pair<Integer, Integer>> assassinPath = MoveController.checkHasLadderPath(startPair, endPair, path);
        LinkedList<Pair<Integer, Integer>> ladderPath = MoveController.checkAssassinPath(startPair, endPair, path);


        if (path == null && assassinPath == null && ladderPath == null) {
            return false;
        }
        if (path == null) {
            for (Military military : militaries) {
                if (military.isUsesLadder()) {
                    Move move = new Move(military.getX(), military.getY(), endPair, true, military);
                    move.setPath(ladderPath);
                    military.setMove(move);

                }

                if (military.isUsesLadder()) {
                    Move move = new Move(military.getX(), military.getY(), endPair, true, military);
                    move.setPath(assassinPath);
                    military.setMove(move);

                }
            }
        } else {
            for (Military military : militaries) {
                Move move = new Move(military.getX(), military.getY(), endPair, true, military);
                move.setPath(path);
                military.setMove(move);
            }
        }
        return true;
    }

    public static boolean attack(Military enemy) {
        Pair<Integer, Integer> startPair = MoveController.getStartPair();
        Pair<Integer, Integer> endPair = new Pair<>(enemy.getY(), enemy.getX());
        LinkedList<Pair<Integer, Integer>> path = MoveController.checkHasPath(startPair, endPair);
        LinkedList<Pair<Integer, Integer>> assassinPath = MoveController.checkHasLadderPath(startPair, endPair, path);
        LinkedList<Pair<Integer, Integer>> ladderPath = MoveController.checkAssassinPath(startPair, endPair, path);

        if (path == null && assassinPath == null && ladderPath == null) {
            return false;
        }

        if (path == null) {
            for (Military military : militaries) {
                if (military.isUsesLadder()) {
                    Move move = new Move(military.getX(), military.getY(), enemy, false, military);
                    move.setPath(ladderPath);
                    military.setMove(move);

                }

                if (military.isUsesLadder()) {
                    Move move = new Move(military.getX(), military.getY(), enemy, false, military);
                    move.setPath(assassinPath);
                    military.setMove(move);

                }
            }
        } else {
            for (Military military : militaries) {
                Move move = new Move(military.getX(), military.getY(), enemy, false, military);
                move.setPath(path);
                military.setMove(move);
            }
        }
        return true;
    }

    public static boolean patrolUnit(int x1, int y1, int x2, int y2) {

        Pair<Integer, Integer> patrolPair = new Pair<>(x2, y2);
        Pair<Integer, Integer> endPair = new Pair<>(x1, y1);
        Pair<Integer, Integer> startPair = MoveController.getStartPair();
        LinkedList<Pair<Integer, Integer>> path = MoveController.checkHasPath(startPair, endPair);
        LinkedList<Pair<Integer, Integer>> assassinPath = MoveController.checkHasLadderPath(startPair, endPair, path);
        LinkedList<Pair<Integer, Integer>> ladderPath = MoveController.checkAssassinPath(startPair, endPair, path);

        if (path == null && assassinPath == null && ladderPath == null) {
            return false;
        }

        if (!MoveController.checkPatrolPath(endPair, patrolPair)) {
            return false;
        }
        if (path == null) {
            for (Military military : militaries) {
                if (military.isUsesLadder()) {
                    Move move = new Move(military.getX(), military.getY(), endPair, true, military);
                    move.setPath(ladderPath);
                    move.setMovePatrol(patrolPair);
                    military.setMove(move);
                }

                if (military.isUsesLadder()) {
                    Move move = new Move(military.getX(), military.getY(), endPair, true, military);
                    move.setPath(assassinPath);
                    move.setMovePatrol(patrolPair);
                    military.setMove(move);

                }
            }
        } else {
            for (Military military : militaries) {
                Move move = new Move(military.getX(), military.getY(), endPair, true, military);
                move.setPath(path);
                move.setMovePatrol(patrolPair);
                military.setMove(move);
            }
        }
        return true;
    }
    public static boolean deactivatePatrol() {
        int counter = 0;
        for (Military military : militaries) {
            if (military.getMove().getMoveState().equals(MoveStates.PATROL.getState())){
                counter++;
                military.getMove().stopMove();
            }
        }
        return counter != 0;
    }
    public static void setState(String state,ArrayList<Military> militaries){
        for (Military military : militaries) {
            if (military.getMove().getMoveState().equals(MoveStates.PATROL.getState())){
                military.setState(state);
            }
        }
    }
}

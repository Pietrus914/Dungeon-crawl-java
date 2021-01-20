package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Demon;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.utils.RandomProvider;

import java.util.ArrayList;
import java.util.List;

public class MonsterPlacer {
    private GameMap map;

    private int mapNumber;
    private int ghostCounter;
    private int demonCounter;
    private final int ghostNumber;
    private final int demonNumber;
    private List<Actor> monsters = new ArrayList<>();


    public MonsterPlacer(GameMap map, int mapNumber) {
        this.map = map;
        this.mapNumber = setMapNumber(mapNumber);
        this.ghostCounter = 0;
        this.demonCounter = 0;
        this.ghostNumber = 4;
        this.demonNumber = 2;
    }

    private int setMapNumber(int mapNumber) {
        int number =0;
        switch (mapNumber){
            case 1:
                number = 1;
                break;
            case 2:
                number = 2;
                break;
            default:
                number = 3;
        }
        return number;
    }

    private boolean enoughGhost() {
        return ghostCounter >= ghostNumber;
    }

    private boolean enoughDemon() {
        return demonCounter >= demonNumber;
    }

    public void addGhostRandomly() {
        boolean cellFoundGhost = false;
        while (!cellFoundGhost) {
            int x = RandomProvider.getRandomNumberOfRange(1, map.getWidth() - 1);
            int y = RandomProvider.getRandomNumberOfRange(1, map.getHeight() - 1);
            Cell cell = map.getCell(x, y);
            if (cell.canAddItem()) {
                Ghost ghost = new Ghost(cell);
                cellFoundGhost = true;
                monsters.add(ghost);
            }
        }
    }

    public void addDemonRandomly() {
        boolean cellFoundDemon = false;
        while (!cellFoundDemon) {
            int x = RandomProvider.getRandomNumberOfRange(1, map.getWidth() - 1);
            int y = RandomProvider.getRandomNumberOfRange(1, map.getHeight() - 1);
            Cell cell = map.getCell(x, y);
            if (cell.canAddItem()) {
                Demon demon = new Demon(cell);
                cellFoundDemon = true;
                monsters.add(demon);
            }
        }
    }

    public void addAllMonsters() {
        while (!enoughGhost()) {
            addGhostRandomly();
            ghostCounter++;
        }
        while (!enoughDemon()) {
            addDemonRandomly();
            demonCounter++;
        }
    }

    public List<Actor> getMonsters() {
        return monsters;
    }
}
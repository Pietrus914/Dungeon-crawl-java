package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Demon;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.utils.RandomProvider;

public class MonsterPlacer {
    private GameMap map;

    private int mapNumber;
    private int ghostCounter;
    private int demonCounter;
    private final int ghostNumber;
    private final int demonNumber;


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
                new Ghost(cell);
                cellFoundGhost = true;
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
                new Demon(cell);
                cellFoundDemon = true;
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
}
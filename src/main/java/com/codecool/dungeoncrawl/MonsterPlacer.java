package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Demon;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.items.Helmet;
import com.codecool.dungeoncrawl.logic.utils.RandomProvider;

import java.util.ArrayList;
import java.util.List;

public class MonsterPlacer {
    private GameMap map;

    private int ghostCounter;
    private int demonCounter;
    private int ghostNumber;
    private int demonNumber;


    public MonsterPlacer(GameMap map) {
        this.map = map;
        this.ghostCounter = 0;
        this.demonCounter = 0;
        this.ghostNumber = 4;
        this.demonNumber = 2;
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
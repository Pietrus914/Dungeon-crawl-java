package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.ItemsPlacer;
import com.codecool.dungeoncrawl.MonsterPlacer;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.ItemsFactory;
import com.codecool.dungeoncrawl.logic.utils.RandomProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameWorld {
    private List<Actor> monsterList;
    private ArrayList<GameMap> levels;
    private File folder = new File("src/main/resources/levels");
    private File[] listOfFiles;
    private GameMap currentMap;
    private List<Item> itemList;

    public GameWorld() {
        File folder = new File("src/main/resources/levels");
        this.monsterList = new ArrayList<>();
        this.levels = new ArrayList<>();
        this.listOfFiles = folder.listFiles();
    }

    public void createLevels() {
        int id = 1;
        for (File listOfFile : Objects.requireNonNull(listOfFiles)) {
            String fileName = listOfFile.getName();
            GameMap newMap = MapLoader.loadMap("/levels/" + fileName);
            levels.add(newMap);
            int mapNumber = Integer.parseInt(String.valueOf(fileName.charAt(fileName.length() - 5)));
            newMap.setMapNumber(mapNumber);

            ItemsPlacer newItemPlacer = new ItemsPlacer(newMap);
            MonsterPlacer monsterPlacer = new MonsterPlacer(newMap, id);
            newMap.setMonsterPlacer(monsterPlacer);
            newItemPlacer.addItemsRandomly();
            monsterPlacer.addAllMonsters();
            id = monsterPlacer.getId();
            monsterList.addAll(monsterPlacer.getMonsters());
        }
        itemList = ItemsFactory.getItems();
        currentMap = levels.get(0);
    }

    public void changeLevel() {
        if (currentMap.getPlayer().getCell().getBuilding() != null) {
            if (currentMap.getPlayer().getCell().getBuilding().getTileName().equals("ladder up")) {
                Player samePlayer = currentMap.getPlayer();
                currentMap = levels.get(levels.indexOf(currentMap) + 1);
                currentMap.setPlayer(samePlayer);
                currentMap.getCell(currentMap.getGoDownX(), currentMap.getGoDownY()).setActor(samePlayer);
                samePlayer.setCellForActor(currentMap.getCell(currentMap.getGoDownX(), currentMap.getGoDownY()));
                CurrentStatus.getInstance().setStatus("Level " + (levels.indexOf(currentMap) + 1));
            } else if (currentMap.getPlayer().getCell().getBuilding().getTileName().equals("ladder down")) {
                Player samePlayer = currentMap.getPlayer();
                currentMap = levels.get(levels.indexOf(currentMap) - 1);
                currentMap.setPlayer(samePlayer);
                currentMap.getCell(currentMap.getGoUpX(), currentMap.getGoUpY()).setActor(samePlayer);
                samePlayer.setCellForActor(currentMap.getCell(currentMap.getGoUpX(), currentMap.getGoUpY()));
                CurrentStatus.getInstance().setStatus("Level " + (levels.indexOf(currentMap) + 1));
            }
        }
    }

    public void moveMonsters() {
        for (Actor monster : monsterList) {
            if (monster.getHealth() > 0 && !monster.getTileName().equals("skeleton")) {
                try {
                    monster.move(RandomProvider.getRandomNumberOfRange(-1, 2), RandomProvider.getRandomNumberOfRange(-1, 2));
                } catch (IllegalStateException | ArrayIndexOutOfBoundsException e) {
            }
        }
    }
}

    public List<Actor> getMonsterList() {
        return monsterList;
    }

    public void setMonsterList(List<Actor> monsterList) {
        this.monsterList = monsterList;
    }

    public ArrayList<GameMap> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<GameMap> levels) {
        this.levels = levels;
    }

    public File getFolder() {
        return folder;
    }

    public void setFolder(File folder) {
        this.folder = folder;
    }

    public File[] getListOfFiles() {
        return listOfFiles;
    }

    public void setListOfFiles(File[] listOfFiles) {
        this.listOfFiles = listOfFiles;
    }

    public GameMap getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(GameMap currentMap) {
        this.currentMap = currentMap;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}

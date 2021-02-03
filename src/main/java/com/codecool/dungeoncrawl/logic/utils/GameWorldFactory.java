package com.codecool.dungeoncrawl.logic.utils;

import com.codecool.dungeoncrawl.ItemsPlacer;
import com.codecool.dungeoncrawl.MonsterPlacer;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.GameWorld;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.ItemsFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameWorldFactory {
    public static GameWorld create() {
        List<Actor> monsterList = new ArrayList<>();
        List<GameMap> levels = new ArrayList<>();
        File folder = new File("src/main/resources/levels");
        File[] listOfFiles = folder.listFiles();
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
        return new GameWorld(levels, levels.get(0), monsterList, ItemsFactory.getItems());
    }
}

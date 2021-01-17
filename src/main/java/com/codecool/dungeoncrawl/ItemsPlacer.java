package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Helmet;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.ItemNames;
import com.codecool.dungeoncrawl.logic.items.ItemsFactory;
import com.codecool.dungeoncrawl.logic.utils.RandomProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemsPlacer {
    private GameMap map;

    private int itemCounter;
    private int mapNumber;
    private int itemsNumber = 3;

    private static Map<Integer, ItemNames[]> itemsContainer = new HashMap<>();
    static {
        itemsContainer.put(1, new ItemNames[]{ItemNames.SWORD, ItemNames.HELMET});


    }




    public ItemsPlacer(GameMap map, int mapNumber) {
        this.map = map;
        this.mapNumber = mapNumber;
        this.itemsNumber = setItemsNumber(mapNumber);
        this.itemCounter = 0;
    }

    private int setItemsNumber(int mapNumber) {
        int number =0;
        switch (mapNumber){
            case 1:
                number = 3;
                break;
            case 2:
                number = 5;
                break;
            default:
                number = 6;
        }
        return number;
    }


    public void addItemsRandomly(){
        if (!enoughItems()){
            boolean cellFound = false;
            while (!cellFound){
                int x = RandomProvider.getRandomNumberOfRange(1,map.getWidth() -1);
                int y = RandomProvider.getRandomNumberOfRange(1,map.getHeight() -1);
                Cell cell = map.getCell(x,y);
                if (cell.canAddItem()){
                    ItemsFactory.createItem(cell, ItemNames.HELMET); // for now only helmet is added randomly
//
                    cellFound = true;
                    itemCounter ++;
                }
            }
        }
    }

    private boolean enoughItems(){
        return itemCounter >= itemsNumber;
    }

}

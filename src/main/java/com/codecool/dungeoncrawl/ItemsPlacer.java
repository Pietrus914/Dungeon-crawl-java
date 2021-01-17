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

    private static Map<Integer, ItemNames[]> itemsContainer = new HashMap<>();
    static {
        itemsContainer.put(1, new ItemNames[]{ItemNames.SWORD, ItemNames.HELMET});
        itemsContainer.put(2,new ItemNames[]{ItemNames.MEAT, ItemNames.GLOVES, ItemNames.SHIELD});
        itemsContainer.put(3, new ItemNames[]{ItemNames.MEDICINE,ItemNames.HELMET, ItemNames.SWORD});
    }

    private GameMap map;

//    private int itemCounter;
    private int mapNumber;
//    private int itemsNumber = 3;






    public ItemsPlacer(GameMap map, int mapNumber) {
        this.map = map;
        this.mapNumber = setMapNumber(mapNumber);
//        this.itemsNumber = setItemsNumber(mapNumber);
//        this.itemCounter = 0;
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


    public void addItemsRandomly(){
        for (ItemNames name : itemsContainer.get(mapNumber)){
            boolean cellFound = false;
            while (!cellFound){
                int x = RandomProvider.getRandomNumberOfRange(1,map.getWidth() -1);
                int y = RandomProvider.getRandomNumberOfRange(1,map.getHeight() -1);
                Cell cell = map.getCell(x,y);
                if (cell.canAddItem()){
//                    ItemsFactory.createItem(cell, ItemNames.HELMET); // for now only helmet is added randomly
                    ItemsFactory.createItem(cell, name);
                    cellFound = true;
//                    itemCounter ++;
                }
            }
        }


    }

//    private boolean enoughItems(){
//        return itemCounter >= itemsNumber;
//    }

}

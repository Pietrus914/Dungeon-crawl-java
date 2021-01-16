package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Helmet;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.utils.RandomProvider;

import java.util.ArrayList;
import java.util.List;

public class ItemsPlacer {
    private GameMap map;

    private int itemCounter;
    private static int itemsNumber = 3;

    private List<Item> itemList = new ArrayList<>();



    public ItemsPlacer(GameMap map) {
        this.map = map;
        this.itemCounter = 0;
    }


    public void addItemsRandomly(){
        if (!enoughItems()){
            boolean cellFound = false;
            while (!cellFound){
                int x = RandomProvider.getRandomNumberOfRange(1,map.getWidth() -1);
                int y = RandomProvider.getRandomNumberOfRange(1,map.getHeight() -1);
                Cell cell = map.getCell(x,y);
                if (cell.canAddItem()){
                    new Helmet(cell);  // for now only helmet is added randomly
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

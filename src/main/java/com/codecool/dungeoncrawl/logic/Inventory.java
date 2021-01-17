package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> inventoryList;

    public Inventory(){
        inventoryList = new ArrayList<>();

    }

    public void add(Item item){
        inventoryList.add(item);
    }


}

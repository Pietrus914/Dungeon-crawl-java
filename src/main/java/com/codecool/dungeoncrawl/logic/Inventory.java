package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {
    private List<Item> inventoryList;

    public Inventory(){
        inventoryList = new ArrayList<>();

    }

    public void add(Item item){
        inventoryList.add(item);
    }

    public ArrayList<String> getItemsNames(){
        List<String> itemsNames = inventoryList.stream()
                .map(Item::getName)
                .collect(Collectors.toList());
        return (ArrayList<String>) itemsNames;
    }


    public void remove(Item item) {
        inventoryList.remove(item);
    }

    public ArrayList<Item> getItems() {
        return (ArrayList<Item>) inventoryList;
    }

    public void removeKey() {
        System.out.println(inventoryList.stream().map(Item::getName));
        for (int i = 0; i < inventoryList.size(); i++) {
            if (inventoryList.get(i).getName().equals("key")) {
                inventoryList.remove(i);
                break;
            }
        }
    }
}

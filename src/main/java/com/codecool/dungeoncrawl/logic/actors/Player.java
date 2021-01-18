package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class Player extends Actor {

    private Inventory inventory;

    public Player(Cell cell) {

        super(cell);
        this.inventory = new Inventory();
    }

    public String getTileName() {
        return "player";
    }

    public void addToInventory(Item item){
        inventory.add(item);
    }

    public ArrayList<String> getInventoryItemsNames(){
        return inventory.getItemsNames();
    }

    public ArrayList<Item> getInventoryItems(){
        return inventory.getItems();
    }

    public void removeItemFromInventory(Item item){
        inventory.remove(item);
    }
}

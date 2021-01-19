package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
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

    @Override
    public void move(int dx, int dy) {
        Cell cell = getCell();
        Cell nextCell = cell.getNeighbor(dx, dy);
        if ((nextCell.getType() == CellType.FLOOR || nextCell.getType().equals(CellType.OPENDOOR)) && nextCell.getActor() == null) {
            cell.setActor(null);
            setCell(cell);
            nextCell.setActor(this);
            cell = nextCell;
            setCell(cell);
        } else if (nextCell.getType().equals(CellType.DOWN) || nextCell.getType().equals(CellType.UP)) {
            cell.setActor(null);
            setCell(cell);
            nextCell.setActor(this);
            cell = nextCell;
            setCell(cell);
        } else if (nextCell.getType().equals(CellType.DOOR) && getInventoryItemsNames().contains("key")) {
            nextCell.setType(CellType.OPENDOOR);
        } else {
            throw new IllegalStateException("You shall not pass !");
        }
    }
}

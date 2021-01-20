package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.DevelopersNames;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.buildings.BuildingsName;
import com.codecool.dungeoncrawl.logic.buildings.OpenDoor;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class Player extends Actor {

    private String name;
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

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void move(int dx, int dy) {
        Cell cell = getCell();
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType().equals(CellType.FLOOR) && nextCell.getBuilding() != null
                && nextCell.getBuilding().getTileName().equals("close door")) {
            if (getInventoryItemsNames().contains("key")) {
                cell.setActor(null);
                setCell(cell);
                nextCell.setActor(this);
                cell = nextCell;
                cell.setBuilding(new OpenDoor(cell, BuildingsName.OPENDOOR));
                setCell(cell);
            }
        } else if (nextCell.getType() == CellType.FLOOR && nextCell.getActor() == null || (
                DevelopersNames.getDevelopersNames().contains(this.getName()) && (
                        nextCell.getType().equals(CellType.EMPTY) || nextCell.getType().equals(CellType.WALL))
                )) {
            cell.setActor(null);
            setCell(cell);
            nextCell.setActor(this);
            cell = nextCell;
            setCell(cell);
        } else {
            throw new IllegalStateException("You shall not pass !");
        }
    }
}

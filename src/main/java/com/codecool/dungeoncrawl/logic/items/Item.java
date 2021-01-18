package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    private final ItemNames itemName;
    protected String message;
    private Cell cell;

    public Item(Cell cell, ItemNames itemName){
        this.cell = cell;
        this.cell.setItem(this);
        this.itemName = itemName;

    }


    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public String getName() {
        return itemName.getItemName();
    }

    public String getMessage(){
        return message;
    };
}

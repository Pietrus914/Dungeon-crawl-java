package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Helmet extends Item {
    public Helmet(Cell cell){
        super(cell, ItemNames.HELMET);

    }

    @Override
    public String getTileName() {
        return ItemNames.HELMET.getItemName();
    }
}

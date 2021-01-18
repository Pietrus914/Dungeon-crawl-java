package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Helmet extends Item {
    public Helmet(Cell cell){
        super(cell, ItemNames.HELMET);
        super.message = "New helmet added to inventory \n +5 to armor !";

    }

    @Override
    public String getTileName() {
        return ItemNames.HELMET.getItemName();
    }
}

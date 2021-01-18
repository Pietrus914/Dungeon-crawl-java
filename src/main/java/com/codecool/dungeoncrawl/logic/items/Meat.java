package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Meat extends Item {
    public Meat(Cell cell) {
        super(cell, ItemNames.MEAT);

    }

    @Override
    public String getTileName() {
        return ItemNames.MEAT.getItemName();
    }
}

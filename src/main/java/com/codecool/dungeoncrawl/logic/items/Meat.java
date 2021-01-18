package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Meat extends Item {
    public Meat(Cell cell) {
        super(cell, ItemNames.MEAT);
        super.message = "Your health increased by 5!";

    }

    @Override
    public String getTileName() {
        return ItemNames.MEAT.getItemName();
    }
}

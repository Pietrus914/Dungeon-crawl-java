package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Shield extends Item {
    public Shield(Cell cell) {
        super(cell, ItemNames.SHIELD);
        super.message = "New shield added to inventory \n +5 to armor !";

    }

    @Override
    public String getTileName() {
        return ItemNames.SHIELD.getItemName();
    }
}

package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Shield extends Item {
    public Shield(Cell cell) {
        super(cell, ItemNames.SHIELD);

    }

    @Override
    public String getTileName() {
        return ItemNames.SHIELD.getItemName();
    }
}

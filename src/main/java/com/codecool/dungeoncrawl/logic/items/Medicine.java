package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Medicine extends Item {
    public Medicine(Cell cell) {
        super(cell, ItemNames.MEDICINE);
        super.message = "Your health increased by  6!";

    }

    @Override
    public String getTileName() {
        return ItemNames.MEDICINE.getItemName();
    }
}

package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Shield extends Item {
    public Shield(Cell cell) {
        super(cell, ItemNames.SHIELD);
        super.points = 4;
        super.message = "New shield added to inventory \n +" + points + " to armor !";

    }

    @Override
    public String getTileName() {
        return ItemNames.SHIELD.getItemName();
    }

    @Override
    public void getImpactOnPlayer() {
        this.getCell().getActor().increaseArmor(points);
    }
}

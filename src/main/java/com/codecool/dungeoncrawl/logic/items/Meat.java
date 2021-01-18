package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Meat extends Item {
    public Meat(Cell cell) {
        super(cell, ItemNames.MEAT);
        super.points = 7;
        super.message = "Your health increased by " + points + " !";

    }

    @Override
    public String getTileName() {
        return ItemNames.MEAT.getItemName();
    }

    @Override
    public void getImpactOnPlayer() {
        this.getCell().getActor().increaseHealth(points);
    }
}

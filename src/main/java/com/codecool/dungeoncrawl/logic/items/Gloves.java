package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Gloves extends Item {


    public Gloves(Cell cell) {
        super(cell, ItemNames.GLOVES);

        super.points = 4;
        super.message = "New gloves added to inventory \n +" +  this.points + " to strength !";


    }

    @Override
    public String getTileName() {
        return ItemNames.GLOVES.getItemName();
    }

    @Override
    public void getImpactOnPlayer() {
        this.getCell().getActor().increaseStrength(points);
    }
}

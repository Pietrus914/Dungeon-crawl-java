package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item{
    public Sword(Cell cell) {
        super(cell, ItemNames.SWORD);
        super.points = 4;
        super.message = "New sword added to inventory \n +" + this.points +" to armor !";
    }

    @Override
    public String getTileName(){
        return ItemNames.SWORD.getItemName();
    }

    @Override
    public void getImpactOnPlayer() {
        this.getCell().getActor().setArmor(points);
    }
}

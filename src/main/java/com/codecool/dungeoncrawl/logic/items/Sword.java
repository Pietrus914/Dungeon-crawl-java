package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item{
    public Sword(Cell cell) {
        super(cell, ItemNames.SWORD);
        super.message = "New sword added to inventory \n +5 to armor !";
    }

    @Override
    public String getTileName(){
        return ItemNames.SWORD.getItemName();
    }
}

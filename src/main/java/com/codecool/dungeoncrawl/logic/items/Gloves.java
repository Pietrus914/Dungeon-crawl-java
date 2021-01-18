package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Gloves extends Item {


    public Gloves(Cell cell) {
        super(cell, ItemNames.GLOVES);
        super.message = "New gloves added to inventory \n +5 to armor !";


    }

    @Override
    public String getTileName() {
        return ItemNames.GLOVES.getItemName();
    }


}

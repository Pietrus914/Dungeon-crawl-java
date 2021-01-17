package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class ItemsFactory {


    public static Item createItem(Cell cell, ItemNames itemName ){
        Item newItem = null;
        switch (itemName){
            case KEY:
                newItem = new Key(cell);
                break;
            case SWORD:
                newItem = new Sword(cell);
                break;
            case HELMET:
                newItem = new Helmet(cell);
                break;
            case SHIELD:
                newItem = new Shield(cell);
                break;
            case MEAT:
                newItem = new Meat(cell);
                break;
            case GLOVES:
                newItem = new Gloves(cell);
                break;
            case MEDICINE:
                newItem = new Medicine(cell);
                break;
            default:
                throw new IllegalArgumentException("itemName not available");
        }

        return newItem;
    }


}
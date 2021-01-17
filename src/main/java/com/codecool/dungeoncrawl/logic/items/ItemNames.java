package com.codecool.dungeoncrawl.logic.items;

public enum ItemNames {
    HELMET("helmet"),
    SWORD("sword"),
    KEY("key");

    private final String itemName;

    ItemNames(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

}

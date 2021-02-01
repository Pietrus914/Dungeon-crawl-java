package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    private int id;
    private final ItemNames itemName;
    protected String message;
    private Cell cell;
    protected int points;
    private int playerId;
    private int mapNumber;

    public Item(Cell cell, ItemNames itemName, int mapNumber){
        this.cell = cell;
        this.cell.setItem(this);
        this.itemName = itemName;
//        this.playerId = 5;
        this.mapNumber = mapNumber;
        setId();

    }

    public int getId() {
        return id;
    }

    public void setId() {
        //TODO
        this.id = 2;
    }

    public ItemNames getItemName() {
        return itemName;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public String getName() {
        return itemName.getItemName();
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }

    public void getImpactOnPlayer(){};
}

package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.items.Item;

public class ItemModel extends BaseModel {

    private int playerId;
    private int mapNumber;

    private String name;
    private String message;
    private int x;
    private int y;
    private int points;

    public ItemModel(Item item){
        this.name = item.getName();
        this.message = item.getMessage();
        this.x = item.getX();
        this.y = item.getY();
        this.points = item.getPoints();
        this.playerId = item.getPlayerId();
        this.mapNumber = item.getMapNumber();
    }

    public ItemModel(int playerId, int mapNumber, String name, String message,
                     int x, int y, int points){
        this.playerId = playerId;
        this.mapNumber = mapNumber;
        this.name = name;
        this.message = message;
        this.x = x;
        this.y = y;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
}

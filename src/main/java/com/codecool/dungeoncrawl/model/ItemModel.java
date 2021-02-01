package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.List;

public class ItemModel extends BaseModel {

    private int playerId;
    private int mapNumber;

    private String name;
    private String message;
    private int x;
    private int y;
    private int points;
    boolean inInventory;

    public ItemModel(Item item , int playerId, Player player){
        this.name = item.getName();
        this.message = item.getMessage();
        this.x = item.getX();
        this.y = item.getY();
        this.points = item.getPoints();
        this.playerId = playerId;
        this.mapNumber = item.getMapNumber();
        this.inInventory = checkInventory(player.getInventory().getItems(), item);
    }

    public ItemModel(int playerId, int mapNumber, String name, String message,
                     int x, int y, int points, boolean inInventory){
        this.playerId = playerId;
        this.mapNumber = mapNumber;
        this.name = name;
        this.message = message;
        this.x = x;
        this.y = y;
        this.points = points;
        this.inInventory = inInventory;
    }

    private boolean checkInventory(List<Item> itemList, Item item){
        if (itemList.contains(item)){
            return true;
        }
        return false;
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

    public boolean isInInventory() {
        return inInventory;
    }

    public void setInInventory(boolean inInventory) {
        this.inInventory = inInventory;
    }
}

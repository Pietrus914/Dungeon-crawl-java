package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.items.Item;

public class ItemModel extends BaseModel {


    private int id;
    private String name;
    private String message;
    private int x;
    private int y;
    private int points;
    private int game_state_id;
    private int mapNumber;

    public ItemModel(Item item , int game_state_id){
        this.id = item.getId();
        this.name = item.getName();
        this.message = item.getMessage();
        this.x = item.getX();
        this.y = item.getY();
        this.points = item.getPoints();
        this.game_state_id = game_state_id;
        this.mapNumber = item.getMapNumber();
    }

    public ItemModel(int id, int game_state_id, int mapNumber, String name, String message,
                     int x, int y, int points){
        this.id = id;
        this.game_state_id = game_state_id;
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getGame_state_id() {
        return game_state_id;
    }

    public void setGame_state_id(int game_state_id) {
        this.game_state_id = game_state_id;
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }
}

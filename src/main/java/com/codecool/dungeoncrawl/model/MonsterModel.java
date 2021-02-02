package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Actor;

public class MonsterModel extends BaseModel{
    private String monsterName;
    private int hp;
    private int x;
    private int y;
    private int mapNumber;
    private int gameStateId;

    public MonsterModel(Actor actor, int gameStateId) {
        this.monsterName = actor.getTileName();
        this.hp = actor.getHealth();
        this.x = actor.getX();
        this.y = actor.getY();
        this.mapNumber = actor.getMapNumber();
        this.gameStateId = gameStateId;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }

    public int getGameStateId() {
        return gameStateId;
    }

    public void setGameStateId(int gameStateId) {
        this.gameStateId = gameStateId;
    }
}

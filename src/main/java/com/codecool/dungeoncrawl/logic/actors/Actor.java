package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;

    private int health = 10;
    private int strength = 1;
    private int armor = 0;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType() == CellType.FLOOR && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.getType().equals(CellType.DOWN) || nextCell.getType().equals(CellType.UP)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else {
            throw new IllegalStateException("You shall not pass !");
        }

    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getStrength() {
        return strength;
    }

    public int getArmor() {
        return armor;
    }

    public int getHealth() {
        return health;
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

    public void setCellForActor(Cell cell){
        this.cell = cell;
    }
}

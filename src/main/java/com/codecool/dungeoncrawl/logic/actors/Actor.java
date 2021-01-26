package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.function.Consumer;

public abstract class Actor implements Drawable {
    private Cell cell;
    private final int maxHealth = 10;
    private int health = 10;
    private int strength = 1;
    private int armor = 0;
    private Consumer<Integer> onHealthChange = null;
    private Consumer<Integer> onStrengthChange = null;
    private Consumer<Integer> onArmorChange = null;

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
        }
//        else {
//            throw new IllegalStateException("You shall not pass !");
//        }
    }

    public void fight(int dx, int dy) {
        Cell cell = getCell();
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor player = cell.getActor();
        Actor monster = nextCell.getActor();

        int monsterHp = monster.getHealth();
        monster.setHealth(monsterHp - player.getStrength());
        player.counterAttack();

//        uncomment below to testing in command line:

//        System.out.println("hit " + player.getStrength());
//        System.out.println("monsterHP " + monsterHp);
//
//        System.out.println("armor " + player.getArmor());
//        System.out.println("playerHP " + player.getHealth());

        if (monsterHp <= 0) {
            nextCell.setActor(null);
        }
    }

    public void counterAttack() {
        Cell cell = getCell();
        Actor player = cell.getActor();
        int playerHp = player.getHealth();
        int playerArmor = player.getArmor();

        if (playerArmor >= 2) {
            player.setArmor(playerArmor - 2);
        } else if (playerArmor == 1) {
            player.setArmor(0);
            player.setHealth(playerHp - 1);
        }  else {
            player.setHealth(playerHp - 2);
        }
    }

    public void setOnHealthChange(Consumer<Integer> onHealthChange){
        this.onHealthChange = onHealthChange;
    }

    public void increaseHealth(int points) {
        this.health += points;
        if (this.onHealthChange != null){
            this.onHealthChange.accept(this.health);
        }
    }

    public void setOnStrengthChange(Consumer<Integer> onStrengthChange){
        this.onStrengthChange = onStrengthChange;
    }

    public void increaseStrength(int points) {
        this.strength += points;
        if (this.onStrengthChange != null){
            this.onStrengthChange.accept(this.strength);
        }
    }

    public void setOnArmorChange(Consumer<Integer> onArmorChange){
        this.onArmorChange = onArmorChange;
    }

    public void increaseArmor(int points) {
        this.armor += points;
        if (this.onArmorChange != null){
            this.onArmorChange.accept(this.armor);
        }
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

    public int getMaxHealth() {
        return maxHealth;
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

    public void setCell(Cell cell) {
        this.cell = cell;
    }


    public void setHealth(int health) {
        this.health = health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}

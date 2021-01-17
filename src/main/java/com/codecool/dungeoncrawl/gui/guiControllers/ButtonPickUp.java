package com.codecool.dungeoncrawl.gui.guiControllers;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class ButtonPickUp extends Button {
    private Player player;

    public ButtonPickUp(GameMap map){
        super("Add to inventory");
        this.player = map.getPlayer();
        this.setTooltip(new Tooltip("Add item to your inventory"));
        this.setFocusTraversable(false);
        this.setOnAction(ignoreEvent -> {
            Cell currentPlayerCell = player.getCell();
            Item itemToGet = currentPlayerCell.getItem();
            currentPlayerCell.setItem(null);
            System.out.println("\n" + ">>>>>>>>>>>>>>>>>>>>Button PickUp pressed");
//            player.addToInventory(itemToGet);
        });

    }

}

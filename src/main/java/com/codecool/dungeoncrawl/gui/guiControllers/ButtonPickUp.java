package com.codecool.dungeoncrawl.gui.guiControllers;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.gui.StatusLine;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CurrentStatus;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.InventoryBoxDisplayer;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.ItemNames;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class ButtonPickUp extends Button {
    private Player player;




    public ButtonPickUp(GameMap map, InventoryBoxDisplayer inventoryBoxDisplayer){
        super("Add to inventory");
        this.player = map.getPlayer();
        this.setTooltip(new Tooltip("Add item to your inventory"));
        this.setFocusTraversable(false);
        this.setOnAction(ignoreEvent -> {
            System.out.println("\n" + ">>>>>>>>>>>>>>>>>>>>Button PickUp pressed");
            addToInventory();
            inventoryBoxDisplayer.refresh();

        });
    }

    private void addToInventory(){
        Cell currentPlayerCell = player.getCell();
        Item itemToGet = currentPlayerCell.getItem();
        currentPlayerCell.setItem(null);
        CurrentStatus.getInstance().setStatus(itemToGet.getMessage());
        player.addToInventory(itemToGet);
    }
}

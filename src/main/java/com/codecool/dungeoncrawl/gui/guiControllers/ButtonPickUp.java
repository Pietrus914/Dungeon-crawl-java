package com.codecool.dungeoncrawl.gui.guiControllers;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
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

    public ButtonPickUp(GameMap map, ListView<String> inventoryListView){
        super("Add to inventory");
        this.player = map.getPlayer();
        this.setTooltip(new Tooltip("Add item to your inventory"));
        this.setFocusTraversable(false);
        this.setDisable(true);
        this.setOnAction(ignoreEvent -> {
            Cell currentPlayerCell = player.getCell();
            Item itemToGet = currentPlayerCell.getItem();
            currentPlayerCell.setItem(null);
            System.out.println("\n" + ">>>>>>>>>>>>>>>>>>>>Button PickUp pressed");
            player.addToInventory(itemToGet);

            ArrayList<String> itemsNames = map.getPlayer().getInventoryItemsNames();
            ObservableList<String> items = FXCollections.observableArrayList(itemsNames);


            inventoryListView.setItems(items);
            inventoryListView.setCellFactory(param -> new ListCell<String>(){
                private ImageView displayImage = new ImageView();
                @Override
                public void updateItem(String name, boolean empty){
                    super.updateItem(name, empty);
                    if (empty){
                        setText(null);
                        setGraphic(null);
                    } else {
                        Canvas canvas = new Canvas(Tiles.TILE_WIDTH, Tiles.TILE_WIDTH);
                        GraphicsContext context = canvas.getGraphicsContext2D();
                        context.fillRect(0,0,1,1);
                        Tiles.drawTileInInventory(context,name, 0,0);
                        setText(name);
                        setGraphic(canvas);
                    }
                }
            });
        });
    }
}

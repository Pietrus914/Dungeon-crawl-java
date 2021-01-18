package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.gui.StatusLine;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CurrentStatus;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.gui.guiControllers.ButtonPickUp;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.io.File;
import java.util.Objects;

public class Main extends Application {
    ArrayList<GameMap> mapList = getLevels();
    GameMap map = mapList.get(0);
    GameCamera gameCamera = new GameCamera(map, 0, 0);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    HBox hbox = new HBox();
    ListView<String> inventoryListView = new ListView<String>();
    Button pickUpButton = new ButtonPickUp(map, inventoryListView);
    StatusLine status = new StatusLine("Let's start the game!");


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        inventoryListView.setPrefHeight(240);
        HBox inventoryHBox = new HBox(inventoryListView);
        inventoryListView.setFocusTraversable(false);


        hbox.getChildren().add(pickUpButton);
        hbox.setPadding(new Insets(35, 0, 35, 0));
        hbox.setAlignment(Pos.CENTER);

        CurrentStatus.getInstance().bind(status::setMessage);
//        text.setFont(Font.font("arial",15));
        HBox infoBox = new HBox(status);

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Inventory: "),0,2);
        ui.add(inventoryHBox,0,3,2,1);
        ui.add(hbox, 0,4, 2,1);
        ui.add(new Label("Status: "), 0, 5);
        ui.add(infoBox,0,6,2,1);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);

        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                changeLevel();
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                changeLevel();
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                changeLevel();
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                changeLevel();
                refresh();
                break;
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gameCamera.centerOnEntity(map.getPlayer());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y, gameCamera);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y, gameCamera);
                } else {
                    Tiles.drawTile(context, cell, x, y, gameCamera);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        setButtonDisable(map.getPlayer().getCell());
    }

    private void setButtonDisable(Cell cell) {
        pickUpButton.setDisable(!cell.getActor().getTileName().equals("player") || cell.getItem() == null);
    }

    private ArrayList<GameMap> getLevels() {
        ArrayList<GameMap> levels = new ArrayList<>();
        File folder = new File("src/main/resources/levels");
        File[] listOfFiles = folder.listFiles();
        for (File listOfFile : Objects.requireNonNull(listOfFiles)) {
            String fileName = listOfFile.getName();
            GameMap newMap = MapLoader.loadMap("/levels/" + fileName);
            levels.add(newMap);
            int mapNumber = 0;
            try {
                mapNumber = Integer.parseInt(String.valueOf(fileName.charAt(fileName.length()-5)));
            } catch (Exception e){
                mapNumber = 1;
            }

            ItemsPlacer newItemPlacer = new ItemsPlacer(newMap,mapNumber );
            MonsterPlacer monsterPlacer = new MonsterPlacer(newMap,mapNumber);
            newItemPlacer.addItemsRandomly();
            monsterPlacer.addAllMonsters();
        }
        return levels;
    }

    private void changeLevel() {

        if (map.getPlayer().getCell().getTileName().equals("ladder up")) {
            Player samePlayer = map.getPlayer();
            map = mapList.get(mapList.indexOf(map) + 1);
            map.setPlayer(samePlayer);
            map.getCell(map.getGoDownX(), map.getGoDownY()).setActor(samePlayer);
            samePlayer.setCellForActor(map.getCell(map.getGoDownX(), map.getGoDownY()));
        } else if (map.getPlayer().getCell().getTileName().equals("ladder down")) {
            Player samePlayer = map.getPlayer();
            map = mapList.get(mapList.indexOf(map) - 1);
            map.setPlayer(samePlayer);
            map.getCell(map.getGoUpX(), map.getGoUpY()).setActor(samePlayer);
            samePlayer.setCellForActor(map.getCell(map.getGoUpX(), map.getGoUpY()));

        }
    }
}

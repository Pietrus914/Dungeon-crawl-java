package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.gui.EndPopUp;
import com.codecool.dungeoncrawl.gui.SavePopUp;
import com.codecool.dungeoncrawl.gui.StartPopUp;
import com.codecool.dungeoncrawl.gui.StatusLine;
import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.gui.guiControllers.ButtonPickUp;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.ItemsFactory;
import com.codecool.dungeoncrawl.logic.utils.RandomProvider;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.util.Objects;

public class Main extends Application {
    ArrayList<GameMap> mapList = getLevels();
    GameMap map = mapList.get(0);
    GameCamera gameCamera = new GameCamera(map, 0, 0);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label nameLabel = new Label();
    Label healthLabel = new Label();
    Label strengthLabel = new Label();
    Label armorLabel = new Label();
    HBox hbox = new HBox();
    ListView<String> inventoryListView = new ListView<>();

    InventoryBoxDisplayer inventoryBoxDisplayer =
            new InventoryBoxDisplayer(map.getPlayer().getInventory(), inventoryListView);

    Button pickUpButton = new ButtonPickUp(map, inventoryBoxDisplayer);
    StatusLine status = new StatusLine("Let's start the game!");
    HBox infoBox = new HBox(status);
    HBox inventoryHBox = new HBox(inventoryListView);
    List<Item> itemsList;
    GameDatabaseManager dbManager;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setupDbManager();

        StartPopUp.display();
        map.getPlayer().setName(StartPopUp.getPlayerName());

        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        inventoryListView.setPrefHeight(240);
        inventoryListView.setFocusTraversable(false);


        hbox.getChildren().add(pickUpButton);
        hbox.setPadding(new Insets(35, 0, 35, 0));
        hbox.setAlignment(Pos.CENTER);

        CurrentStatus.getInstance().bind(status::setMessage);
        map.getPlayer().setOnHealthChange((Integer health) -> healthLabel.setText("" + health));
        map.getPlayer().setOnStrengthChange((Integer strength) -> strengthLabel.setText("" + strength));
        map.getPlayer().setOnArmorChange((Integer armor) -> armorLabel.setText("" + armor));


        uiAddElements(ui);
        nameLabel.setText(map.getPlayer().getName());
        nameLabel.setStyle("-fx-font-weight: bold;");
        healthLabel.setText("" + map.getPlayer().getHealth());
        strengthLabel.setText("" + map.getPlayer().getStrength());
        armorLabel.setText("" + map.getPlayer().getArmor());

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);

        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void uiAddElements(GridPane ui) {
        ui.add(new Label("Player: "), 0, 0);
        ui.add(nameLabel, 1, 0);
        ui.add(new Label("Health: "), 0, 1);
        ui.add(healthLabel, 1, 1);
        ui.add(new Label("Strength: "), 0, 2);
        ui.add(strengthLabel, 1, 2);
        ui.add(new Label("Armor: "), 0, 3);
        ui.add(armorLabel, 1, 3);

        ui.add(new Label("Inventory: "), 0, 4);
        ui.add(inventoryHBox, 0, 5, 2, 1);
        ui.add(hbox, 0, 6, 2, 1);
        ui.add(new Label("Status: "), 0, 7);
        ui.add(infoBox, 0, 8, 2, 1);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                react(Direction.UP);
                break;
            case DOWN:
                react(Direction.DOWN);
                break;
            case LEFT:
                react(Direction.LEFT);
                break;
            case RIGHT:
                react(Direction.RIGHT);
                break;
        }

    }

    private void react(Direction direction) {
        map.getPlayer().move(direction.getX(), direction.getY());
        changeLevel();
        moveMonsters();
        refresh();

    }

    private void moveMonsters() {
        for (GameMap gameMap : mapList) {
            for (Actor monster : gameMap.getMonsterPlacer().getMonsters()) {
                if (monster.getHealth() > 0) {
                    try {
                        monster.move(RandomProvider.getRandomNumberOfRange(-1, 2), RandomProvider.getRandomNumberOfRange(-1, 2));
                    } catch (IllegalStateException | ArrayIndexOutOfBoundsException e) {

                    }
                } else {
                    mapList.remove(monster);
                }
            }
        }
    }

    private void refresh() {
        if (isDead()){
            EndPopUp.display();
        }
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gameCamera.centerOnEntity(map.getPlayer());
        inventoryBoxDisplayer.refresh();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y, gameCamera);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y, gameCamera);
                } else if (cell.getBuilding() != null) {
                    Tiles.drawTile(context, cell.getBuilding(), x, y, gameCamera);
                } else {
                    Tiles.drawTile(context, cell, x, y, gameCamera);
                }
            }
        }
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
            int mapNumber = Integer.parseInt(String.valueOf(fileName.charAt(fileName.length() - 5)));
            newMap.setMapNumber(mapNumber);

            ItemsPlacer newItemPlacer = new ItemsPlacer(newMap);
            MonsterPlacer monsterPlacer = new MonsterPlacer(newMap);
            newMap.setMonsterPlacer(monsterPlacer);
            newItemPlacer.addItemsRandomly();
            monsterPlacer.addAllMonsters();
        }
        return levels;
    }

    private void changeLevel() {
        if (map.getPlayer().getCell().getBuilding() != null) {
            if (map.getPlayer().getCell().getBuilding().getTileName().equals("ladder up")) {
                Player samePlayer = map.getPlayer();
                map = mapList.get(mapList.indexOf(map) + 1);
                map.setPlayer(samePlayer);
                map.getCell(map.getGoDownX(), map.getGoDownY()).setActor(samePlayer);
                samePlayer.setCellForActor(map.getCell(map.getGoDownX(), map.getGoDownY()));
                CurrentStatus.getInstance().setStatus("Level " + (mapList.indexOf(map) + 1));
            } else if (map.getPlayer().getCell().getBuilding().getTileName().equals("ladder down")) {
                Player samePlayer = map.getPlayer();
                map = mapList.get(mapList.indexOf(map) - 1);
                map.setPlayer(samePlayer);
                map.getCell(map.getGoUpX(), map.getGoUpY()).setActor(samePlayer);
                samePlayer.setCellForActor(map.getCell(map.getGoUpX(), map.getGoUpY()));
                CurrentStatus.getInstance().setStatus("Level " + (mapList.indexOf(map) + 1));
            }
        }
    }

    private boolean isDead() {
        int playerHealth =  map.getPlayer().getHealth();
        return playerHealth <= 0;
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        KeyCombination saveCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        } else if (saveCombination.match(keyEvent)) {
            SavePopUp.display();
            dbManager.savePlayer(map.getPlayer(), SavePopUp.getPlayerName());
            itemsList = ItemsFactory.getItems();
            dbManager.saveItems(itemsList);
        }
    }

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }
}

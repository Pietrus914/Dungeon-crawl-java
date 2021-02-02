package com.codecool.dungeoncrawl.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewGameLoadGamePopup {

    public static void display(){

        Stage window = new Stage();
        window.setOnCloseRequest(windowEvent -> {
            Platform.exit();
        });

        window.initModality(Modality.APPLICATION_MODAL);
        String title = "Hello in Dungeon Crawl!";
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(300);


        Button NewGameButton = new Button("New Game");
        NewGameButton.setPrefSize(200, 50);
        NewGameButton.setOnAction(e -> {
            StartPopUp.display();
            window.close();
        });

        Button LoadGameButton = new Button("Load Game");
        LoadGameButton.setPrefSize(200, 50);
        LoadGameButton.setOnAction(e -> {
            SavedGameList.display();
            window.close();
        });

        VBox layout = new VBox(50);
        layout.getChildren().addAll(NewGameButton, LoadGameButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.rgb(121,77,96), CornerRadii.EMPTY, Insets.EMPTY)));


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

    //public static String getPlayerName() {
    //    return playerName;
    //}
}


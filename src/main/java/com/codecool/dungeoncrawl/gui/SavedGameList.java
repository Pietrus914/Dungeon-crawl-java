package com.codecool.dungeoncrawl.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SavedGameList {
    public static void display(){

        Stage window = new Stage();
        window.setOnCloseRequest(windowEvent -> {
            Platform.exit();
        });

        window.initModality(Modality.APPLICATION_MODAL);
        String title = "Load Game";
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(350);



        VBox layout = new VBox(50);
        layout.getChildren().addAll(        );
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


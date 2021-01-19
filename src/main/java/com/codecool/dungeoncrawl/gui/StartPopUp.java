package com.codecool.dungeoncrawl.gui;

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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StartPopUp {
    private static String title = "Start!";

    public static void display(){

        Stage window = new Stage();

        /**
         * it blocks other windows and get focus to this window
         */
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(350);



        Label label = new Label();
        label.setPadding(new Insets(25,25,25,25));
        label.setText("What is your name?");

        Button startButton = new Button(title);
        startButton.setOnAction(e -> {

            window.close();
        });

        TextField nameField = new TextField();
        nameField.setPadding(new Insets(15,15,15,15));
//        Text text = new Text();
//        text.setText(content);
//        text.setFont(Font.font ("Verdana", 15));
//        text.setFill(Color.BLUEVIOLET);
//        text.setWrappingWidth(250);
//        text.setTextAlignment(TextAlignment.JUSTIFY);


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, nameField, startButton);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(25,25,25,25));
        layout.setBackground(new Background(new BackgroundFill(Color.PERU, CornerRadii.EMPTY, Insets.EMPTY)));
        ;


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}

package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.gui.guiControllers.ButtonExport;
import com.codecool.dungeoncrawl.gui.guiControllers.ButtonImport;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.VLineTo;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class SavePopUp {
    private static String title = "Save Game";
    private static String playerName;

    public static void display(){

        Stage window = new Stage();
        window.setOnCloseRequest(windowEvent -> {
            Platform.exit();
        });

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("C:\\DATA"));
        FileChooser fileChooser = new FileChooser();




        /**
         * it blocks other windows and get focus to this window
         */
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(350);

        Label label = new Label();
        label.setPadding(new Insets(25,25,25,25));
        label.setText("Name:");
        label.setStyle("-fx-font-weight: bold;");

        TextField nameField = new TextField();
        nameField.setPadding(new Insets(15,15,15,15));


        Button saveButton = new Button(title);
        saveButton.setOnAction(e -> {
            playerName = nameField.getText();
            window.close();
        });

        Button importButton = new ButtonImport(fileChooser, window);
        Button exportButton = new ButtonExport(fileChooser,window);

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());

        cancelButton.setMinWidth(100);
        saveButton.setMinWidth(100);

        FlowPane buttonNode = new FlowPane(10, 10);
        buttonNode.setAlignment(Pos.CENTER);


        VBox layout = new VBox(10);
        buttonNode.getChildren().addAll(saveButton, importButton, exportButton, cancelButton);
        layout.getChildren().addAll(label, nameField, buttonNode);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(25,25,25,25));
        layout.setBackground(new Background(new BackgroundFill(Color.rgb(121,77,96), CornerRadii.EMPTY, Insets.EMPTY)));


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

    public static String getPlayerName() {
        return playerName;
    }
}

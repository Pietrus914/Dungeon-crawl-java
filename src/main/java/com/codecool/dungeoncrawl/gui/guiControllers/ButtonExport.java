package com.codecool.dungeoncrawl.gui.guiControllers;

import com.codecool.dungeoncrawl.dao.GameJsonManager;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ButtonExport extends Button {

    public ButtonExport(FileChooser fileChooser, Stage primaryStage, GameJsonManager manager){
        super("Export");
        this.setTooltip(new Tooltip("Choose where to save game"));
        this.setMinWidth(100);
        this.setFocusTraversable(false);
        this.setOnAction(e -> {
            System.out.println("\n" + ">>>>>>>>>>>>>>>>>>>>Button Export pressed");
//            String current = System.getProperty("user.name");
//            fileChooser.setInitialDirectory(new File("C:\\Users\\" + current + "\\Documents"));
//            fileChooser.setInitialDirectory(new File("C:\\DATA"));
            fileChooser.setInitialDirectory(new File("C:"));
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName("dungeon_crawl.json");
            File savingFile = fileChooser.showSaveDialog(primaryStage);
            manager.saveToProjectFile(savingFile);
//            primaryStage.close();

        });
    }
}

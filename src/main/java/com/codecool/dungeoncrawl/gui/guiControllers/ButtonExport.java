package com.codecool.dungeoncrawl.gui.guiControllers;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ButtonExport extends Button {

    public ButtonExport(FileChooser fileChooser, Stage primaryStage){
        super("Export");
//        this.player = map.getPlayer();
        this.setTooltip(new Tooltip("Choose where to save game"));
        this.setMinWidth(100);
        this.setFocusTraversable(false);
        this.setOnAction(e -> {
            System.out.println("\n" + ">>>>>>>>>>>>>>>>>>>>Button Export pressed");
            fileChooser.setInitialDirectory(new File("C:\\DATA"));
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName("dungeon_crawl.txt");
            File selectedFile = fileChooser.showSaveDialog(primaryStage);
            System.out.println(selectedFile.getAbsolutePath());

        });
    }
}

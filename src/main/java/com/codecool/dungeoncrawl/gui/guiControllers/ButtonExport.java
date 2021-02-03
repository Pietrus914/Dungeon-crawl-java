package com.codecool.dungeoncrawl.gui.guiControllers;

import com.codecool.dungeoncrawl.dao.GameJsonManager;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.DirectoryChooser;
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
//            String current = System.getProperty("user.home");
//            fileChooser.setInitialDirectory(new File("C:\\Users\\" + current + "\\Documents"));
//            fileChooser.setInitialDirectory(new File("C:\\DATA"));
            fileChooser.setInitialDirectory(new File("C:"));
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName("dungeon_crawl.txt");
            File savingFile = fileChooser.showSaveDialog(primaryStage);
//            System.out.println(selectedFile.getAbsolutePath());
            manager.saveToProjectFile(savingFile);
//            primaryStage.close();

        });
    }
}

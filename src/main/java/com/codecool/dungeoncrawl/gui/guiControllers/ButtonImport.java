package com.codecool.dungeoncrawl.gui.guiControllers;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ButtonImport extends Button {

    public ButtonImport(FileChooser fileChooser, Stage primaryStage){
        super("Import");
//        this.player = map.getPlayer();
        this.setTooltip(new Tooltip("Choose the file to import"));
        this.setMinWidth(100);
        this.setFocusTraversable(false);
        this.setOnAction(e -> {
            System.out.println("\n" + ">>>>>>>>>>>>>>>>>>>>Button Import pressed");
//            String current = System.getProperty("user.dir");
//            fileChooser.setInitialDirectory(new File("C:\\Users\\" + current + "\\Documents"));
//            fileChooser.setInitialDirectory(new File("C:\\DATA"));
            fileChooser.setInitialDirectory(new File("C:"));
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            File selectedDirectory = fileChooser.showOpenDialog(primaryStage);
//                System.out.println(selectedDirectory.getAbsolutePath());
//            primaryStage.close();
            });
    }

}

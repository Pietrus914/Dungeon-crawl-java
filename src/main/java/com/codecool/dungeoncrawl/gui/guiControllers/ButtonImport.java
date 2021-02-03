package com.codecool.dungeoncrawl.gui.guiControllers;

import com.codecool.dungeoncrawl.dao.GameJsonManager;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ButtonImport extends Button {

    public ButtonImport(FileChooser fileChooser, Stage primaryStage, GameJsonManager manager){
        super("Import");
        this.setTooltip(new Tooltip("Choose the file to import"));
        this.setMinWidth(100);
        this.setFocusTraversable(false);
        this.setOnAction(e -> {
            System.out.println("\n" + ">>>>>>>>>>>>>>>>>>>>Button Import pressed");
//            String current = System.getProperty("user.name");
//            fileChooser.setInitialDirectory(new File("C:\\Users\\" + current + "\\Documents"));
//            String current = System.getProperty("user.home");
//            fileChooser.setInitialDirectory(new File("C:\\DATA"));
            fileChooser.setInitialDirectory(new File("C:"));
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extFilter);

            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            manager.importProject(selectedFile);

//            primaryStage.close();
            });
    }

}
